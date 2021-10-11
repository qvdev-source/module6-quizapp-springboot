package com.springbootquiz.repository;

import com.springbootquiz.model.Category;
import com.springbootquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value = "select c from Quiz c where c.title like concat('%',:title,'%')")
    ArrayList<Quiz> findAllByTitle(@Param("title") String title);
}
