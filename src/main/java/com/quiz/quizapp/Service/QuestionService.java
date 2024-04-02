package com.quiz.quizapp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.Dao.QuestionDao;
import com.quiz.quizapp.Model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

  
    public ResponseEntity<List<Question>> getAllQuestions() {
        System.out.println("inside service");
        try{
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        System.out.println("inside service by category");
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(String difficultyLevel) {
        System.out.println("inside service by difficulty level");
        try {
            return new ResponseEntity<>(questionDao.findByDifficultyLevel(difficultyLevel),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        System.out.println("inside service add question");
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Successfully added the question!",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Note able to add the question!",HttpStatus.BAD_REQUEST);
    }

	public String modifyQuestions(Question question, Integer id) {
        System.out.println("inside service modify question");
		question.setId(id);
        questionDao.save(question);
        return "Successfully modified the question!";
	}

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "Successfully deleted the question!";
    }
    
}
