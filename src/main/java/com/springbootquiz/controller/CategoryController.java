package com.springbootquiz.controller;

import com.springbootquiz.model.Category;
import com.springbootquiz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    @GetMapping
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        Optional<Category> categoryOptional = Optional.ofNullable(categoryService.getCategory(categoryId));
        if (categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }



    @GetMapping("/find/{title}")
    public ResponseEntity findEmployeeById(@PathVariable("title") String title) {
        try {
            return new ResponseEntity<>(categoryService.findAllByTitle(title), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
