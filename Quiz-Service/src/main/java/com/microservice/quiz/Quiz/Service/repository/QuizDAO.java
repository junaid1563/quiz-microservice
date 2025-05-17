package com.microservice.quiz.Quiz.Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.quiz.Quiz.Service.entity.Quiz;


@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer>{

}
