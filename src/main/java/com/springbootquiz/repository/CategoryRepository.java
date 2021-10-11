package com.springbootquiz.repository;

import com.springbootquiz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    ArrayList<Category> findAllByTitle(String category);
    @Query(value = "select c from Category c where c.title like concat('%',:title,'%')")
    ArrayList<Category> findAllByTitle(@Param("title") String title);
}
