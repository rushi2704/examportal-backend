package com.examportal.portal.service.impl;

import com.examportal.portal.model.exam.Category;
import com.examportal.portal.model.exam.Quizz;
import com.examportal.portal.repo.QuizzRepository;
import com.examportal.portal.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizzServiceImpl implements QuizzService {

    @Autowired
    private QuizzRepository quizzRepository;

    @Override
    public Quizz addQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Set<Quizz> getQuizzes() {
        return new HashSet<>(this.quizzRepository.findAll());
    }

    @Override
    public Quizz getQuizz(Long quizzId) {
        return this.quizzRepository.findById(quizzId).get();
    }

    @Override
    public void deleteQuizz(Long quizzId) {
//        Quizz quizz = new Quizz();
//        quizz.setqId(quizzId);
        this.quizzRepository.deleteById(quizzId);

    }

    @Override
    public List<Quizz> getQuizzesOfCategory(Category category) {
        return this.quizzRepository.findBycategory(category);
    }
////////////////////////////user api////////////////////////////////////////
    //get Active Quizzes
    @Override
    public List<Quizz> getActiveQuizzes() {
       return this.quizzRepository.findByActive(true);
    }

    @Override
    public List<Quizz> getActiveQuizzesOfCategory(Category c) {
        return this.quizzRepository.findByCategoryAndActive(c,true);
    }



}
