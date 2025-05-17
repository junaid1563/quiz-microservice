package com.microservice.quiz.Quiz.Service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.quiz.Quiz.Service.config.QuestionServiceClient;
import com.microservice.quiz.Quiz.Service.entity.QuestionWrapper;
import com.microservice.quiz.Quiz.Service.entity.Quiz;
import com.microservice.quiz.Quiz.Service.entity.Response;
import com.microservice.quiz.Quiz.Service.repository.QuizDAO;

@Service
public class QuizService {
	@Autowired
	private QuizDAO quizDb;
	@Autowired
	private QuestionServiceClient questionServiceClient;

	public Integer createQuiz(String title, String category, int qCount) {
		System.out.println("Inside ," + category + qCount);
		List<Integer> questionids = questionServiceClient.getQuestionsRand(category, qCount);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionids);
		Quiz q = quizDb.save(quiz);
		return q.getId();
	}

	public List<QuestionWrapper> getQuestions(int quizId) {
		Quiz quiz = quizDb.findById(quizId).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		List<QuestionWrapper> questions = questionServiceClient.getQuestions(questionIds);
		return questions;
	}

	public int calculateScore(List<Response> responses) {
		return questionServiceClient.calculateScore(responses);
	}
}