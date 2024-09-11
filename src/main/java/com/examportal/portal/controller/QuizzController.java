package com.examportal.portal.controller;

import com.examportal.portal.model.exam.Quizz;
import com.examportal.portal.service.QuestionService;
import com.examportal.portal.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizz")
@CrossOrigin("*")
public class QuizzController {

    @Autowired
    private QuizzService quizzService ;

    // add Quizz
    @PostMapping("/")
    public ResponseEntity<Quizz> addQuizz(@RequestBody Quizz quizz)
    {
        return ResponseEntity.ok(this.quizzService.addQuizz(quizz));

    }

    // get all quizz
    @GetMapping("/")
    public ResponseEntity<?> getQuizzs()
    {
        return ResponseEntity.ok(this.quizzService.getQuizzes());
    }

    //  get quizz
    @GetMapping("/{quizzId}")
    public Quizz getQuizz(@PathVariable("quizzId") Long quizzId)
    {
        return this.quizzService.getQuizz(quizzId);
    }

    // update quizz
    @PutMapping("/")
    public ResponseEntity<Quizz> updateQuizz(@RequestBody Quizz quizz)
    {
       return ResponseEntity.ok(this.quizzService.updateQuizz(quizz));
    }

    // delete quizz
    @DeleteMapping("/{quizzId}")
    public void  deleteQuizz(@PathVariable("quizzId") Long quizzId)
    {
        this.quizzService.deleteQuizz(quizzId);
    }
}
