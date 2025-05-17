package com.microservice.question.Question.Service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.question.Question.Service.entity.Question;
import com.microservice.question.Question.Service.entity.QuestionWrapper;
import com.microservice.question.Question.Service.entity.Response;
import com.microservice.question.Question.Service.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService qService;

	@GetMapping("/allQuestions")
	public List<Question> allQuestions() {
		return qService.getAllQuestions();
	}

	@PostMapping("/addQuestions")
	public String addQuestions(@RequestBody List<Question> questions) {
		return qService.addQuestions(questions);
	}

	@GetMapping("/category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable String category) {
		return qService.getQuestionsByCategory(category);
	}

	@GetMapping("/difficulty/{difficulty}")
	public List<Question> getQuestionsByDiffculty(@PathVariable String difficulty) {
		return qService.getQuestionsByDifficulty(difficulty);
	}

	@GetMapping("/getQuestionsRand")
	public List<Integer> getQuestionsRand(@RequestParam String category, @RequestParam int qNum) {
		return qService.getQuestionsRand(category, qNum);
	}

	@PostMapping("/calculateScore")
	public int calculateScore(@RequestBody List<Response> responses) {
		return qService.calculateScore(responses);
	}

	@PostMapping("/getQuestions")
	public List<QuestionWrapper> getQeustion(@RequestBody List<Integer> questionids) {
		return qService.getQuestions(questionids);
	}

}
