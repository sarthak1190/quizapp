package com.quiz.quizapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.Model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{
    
}
