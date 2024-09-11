package com.examportal.portal.controller;

import com.examportal.portal.model.exam.Question;
import com.examportal.portal.model.exam.Quizz;
import com.examportal.portal.service.QuestionService;
import com.examportal.portal.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizzService quizzService;
    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        return  ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    // update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }



    // get single question
    @GetMapping("/{quesid}")
    public Question getQuestion(@PathVariable("quesid") Long quesid)
    {
        return this.questionService.getQuestion(quesid);
    }

    // get question of quizz
    @GetMapping("/quizz/{qid}")
    public ResponseEntity<?> getQuestionOfQuizz(@PathVariable("qid")Long qid)
    {
//        Quizz quizz = new Quizz();
//        quizz.setqId(qid);
//     Set<Question> questionOfQuizz= this.questionService.getQuestionsOfQuizz(quizz);
//     return ResponseEntity.ok(questionOfQuizz);

        Quizz quizz = this.quizzService.getQuizz(qid);
       Set<Question> questions= quizz.getQuestions();
       List list = new ArrayList(questions);
       if(list.size()>Integer.parseInt(quizz.getNumberQuestions()))
       {
           list =list.subList(0,Integer.parseInt(quizz.getNumberQuestions()+1));
       }
        Collections.shuffle(list);
return ResponseEntity.ok(list);
    }

    // get question of quizz
    @GetMapping("/quizz/all/{qid}")
    public ResponseEntity<?> getQuestionOfQuizzAdmin(@PathVariable("qid")Long qid)
    {
        Quizz quizz = new Quizz();
        quizz.setqId(qid);
     Set<Question> questionOfQuizz= this.questionService.getQuestionsOfQuizz(quizz);
     return ResponseEntity.ok(questionOfQuizz);


//        return ResponseEntity.ok(list);
    }

    //delete question
    @DeleteMapping("/{qid}")
    public void deleteQuestion(@PathVariable("qid") Long qid)
    {
        this.questionService.deleteQuestion(qid);
    }


}
