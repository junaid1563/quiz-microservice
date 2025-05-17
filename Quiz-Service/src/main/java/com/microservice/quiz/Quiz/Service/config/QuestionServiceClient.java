package com.microservice.quiz.Quiz.Service.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.quiz.Quiz.Service.entity.QuestionWrapper;
import com.microservice.quiz.Quiz.Service.entity.Response;

@FeignClient("QUESTION-APP")
public interface QuestionServiceClient {

	@GetMapping("/question/getQuestionsRand")
	public List<Integer> getQuestionsRand(@RequestParam String category, @RequestParam int qNum);

	@PostMapping("/question/calculateScore")
	public int calculateScore(@RequestBody List<Response> responses);

	@PostMapping("/question/getQuestions")
	public List<QuestionWrapper> getQuestions(@RequestBody List<Integer> questionids);
}
