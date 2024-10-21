package com.examportal.portal.service;

import com.examportal.portal.model.exam.Category;
import com.examportal.portal.model.exam.Quizz;

import java.util.List;
import java.util.Set;

public interface QuizzService {

    public Quizz addQuizz(Quizz quizz);
    public  Quizz updateQuizz(Quizz quizz);
    public Set<Quizz> getQuizzes();
    public  Quizz getQuizz(Long quizzId);
    public void deleteQuizz(Long quizzId);

    public List<Quizz> getQuizzesOfCategory(Category category);

    public List<Quizz> getActiveQuizzes();
    public List<Quizz> getActiveQuizzesOfCategory(Category c);
}
