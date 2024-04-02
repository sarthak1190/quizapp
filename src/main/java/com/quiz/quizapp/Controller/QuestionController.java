package com.quiz.quizapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.Model.Question;
import com.quiz.quizapp.Service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        System.out.println("inside controller");
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("difficultyLevel/{dl}")
    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(@PathVariable("dl")String difficultyLevel) {
        return questionService.getQuestionByDifficultyLevel(difficultyLevel);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("updateQuestion/{id}")
	public String modifyQuestion(@RequestBody Question question, @PathVariable("id") Integer id) {
		return questionService.modifyQuestions(question, id);	
	}
	
	@DeleteMapping("deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}
}
