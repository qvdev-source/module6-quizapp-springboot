package com.springbootquiz.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question_history")
@Data
public class QuizHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String marksGot;

    private String correctAnswers;

    private String quizId;

    private String quizTitle;
     private String username;

    @Column(name = "time_create")
    private LocalDateTime timeCreate;

}
