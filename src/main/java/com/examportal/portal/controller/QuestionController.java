package com.examportal.portal.controller;

import com.examportal.portal.model.exam.Question;
import com.examportal.portal.model.exam.Quizz;
import com.examportal.portal.service.QuestionService;
import com.examportal.portal.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
       List<Question> list = new ArrayList(questions);
       if(list.size()>Integer.parseInt(quizz.getNumberQuestions()))
       {
           list =list.subList(0,Integer.parseInt(quizz.getNumberQuestions()+1));
       }

       list.forEach((q)->{
           q.setAnswer("");
       });

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


    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
    {
        System.out.print(questions);
        double marksGot=0;
        int correctAnswer=0;
        int attempted=0;

       for(Question q : questions) {
            //get single question

            Question question  = this.questionService.get(q.getqId());
            if(question.getAnswer().equals(q.getGivenAnswer()))
            {
                // if correctx
                correctAnswer++;
                    System.out.print(correctAnswer);
                double marksSingle = Double.parseDouble(questions.get(0).getQuizz().getMaxMarks())/questions.size();
                       // this.questions[0].quizz.maxMarks/this.questions.length;
                    marksGot += marksSingle;
            }

            if(q.getGivenAnswer()!=null){
                attempted++;
            }
        }

       Map<String, Object> map= Map.of("marksGot",marksGot, "corrextAnswer",correctAnswer,"attempted",attempted);
        return ResponseEntity.ok(map);
    }


}
