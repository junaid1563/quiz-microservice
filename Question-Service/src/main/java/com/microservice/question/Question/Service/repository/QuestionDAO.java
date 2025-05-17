package com.microservice.question.Question.Service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.question.Question.Service.entity.Question;


@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

	public List<Question> findByCategory(String category);

	public List<Question> findByDifficultyLevel(String difficultyLevel);

	@Query(value = "select id from question where category=:cat order by rand() limit :questionCount", nativeQuery = true)
	public List<Integer> findCategoryAndQuestionCount(@Param("cat") String cat,@Param("questionCount") int questionCount);
}
