package com.microservice.question.Question.Service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.microservice.question.Question.Service.entity.Question;
import com.microservice.question.Question.Service.entity.QuestionWrapper;
import com.microservice.question.Question.Service.entity.Response;
import com.microservice.question.Question.Service.repository.QuestionDAO;

@Service
public class QuestionService {

	@Autowired
	private QuestionDAO db;
	@Autowired
	private Environment environment;

	public List<Question> getAllQuestions() {
		return db.findAll();
	}

	public String addQuestions(List<Question> questions) {
		questions.forEach(q -> System.out.println(q.getQuestion()));
		db.saveAll(questions);
		return "Questions Added";
	}

	public List<Question> getQuestionsByCategory(String category) {
		return db.findByCategory(category);
	}

	public List<Question> getQuestionsByDifficulty(String difficulty) {
		return db.findByDifficultyLevel(difficulty);
	}

	public List<Integer> getQuestionsRand(String category, int qNum) {
		System.out.println("Environment = " + environment.getProperty("server.port"));
		return db.findCategoryAndQuestionCount(category, qNum);
	}

	public int calculateScore(List<Response> responses) {
		int score = 0;

		for (Response response : responses) {
			Question question = db.findById(response.getQuestionId()).get();
			if (question.getAnswer().equals(response.getSelectedAnswer())) {
				score += 1;
			}
		}
		return score;
	}

	public List<QuestionWrapper> getQuestions(List<Integer> questionIds) {
		List<Question> questions = db.findAllById(questionIds);
		List<QuestionWrapper> questionWrappers = new ArrayList<QuestionWrapper>();
		questions.forEach(question -> {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestion(question.getQuestion());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			questionWrappers.add(wrapper);
		});
		return questionWrappers;
	}
}
