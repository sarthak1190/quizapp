package com.quiz.quizapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.Dao.QuestionDao;
import com.quiz.quizapp.Dao.QuizDao;
import com.quiz.quizapp.Model.Question;
import com.quiz.quizapp.Model.QuestionWrapper;
import com.quiz.quizapp.Model.Quiz;
import com.quiz.quizapp.Model.Response;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOptional = quizDao.findById(id);

        List<Question> questionsFromDB = quizOptional.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz= quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int correctAnswersCount=0;
        int i=0;

        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                correctAnswersCount++;
            }
            i++;
        }

        return new ResponseEntity<>(correctAnswersCount, HttpStatus.OK);
    }

}
