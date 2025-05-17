package com.microservice.quiz.Quiz.Service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.quiz.Quiz.Service.entity.QuestionWrapper;
import com.microservice.quiz.Quiz.Service.entity.Response;
import com.microservice.quiz.Quiz.Service.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	private QuizService service;

	@GetMapping("/createQuiz")
	public int createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam int qCount) {
		return service.createQuiz(title, category, qCount);
	}

	@GetMapping("/getQuestions/{quizId}")
	public List<QuestionWrapper> getQuestions(@PathVariable int quizId) {
		return service.getQuestions(quizId);
	}

	@PostMapping("/calculateScore")
	public int calculateScore(@RequestBody List<Response> responses) {
		return service.calculateScore(responses);
	}
}
