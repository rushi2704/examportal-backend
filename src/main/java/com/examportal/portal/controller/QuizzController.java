package com.examportal.portal.controller;

import com.examportal.portal.model.exam.Category;
import com.examportal.portal.model.exam.Quizz;
import com.examportal.portal.service.QuestionService;
import com.examportal.portal.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get specific category
    @GetMapping("/category/{cid}")
    public List<Quizz> getQuizzesOfCategory(@PathVariable("cid") Long cid)
    {
     Category category= new Category();
     category.setCid(cid);
     return  this.quizzService.getQuizzesOfCategory(category);

    }

    // get active quizzes
    @GetMapping("/active")
    public List<Quizz> getActiveQuizzes()
    {
        return this.quizzService.getActiveQuizzes();
    }
    //get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public List<Quizz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid)
    {
        Category category = new Category();
        category.setCid(cid);
        return this.quizzService.getActiveQuizzesOfCategory(category);
    }
}
