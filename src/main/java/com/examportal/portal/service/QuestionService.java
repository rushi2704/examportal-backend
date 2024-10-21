package com.examportal.portal.service;

import com.examportal.portal.model.exam.Question;
import com.examportal.portal.model.exam.Quizz;

import java.util.Set;

public interface QuestionService  {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionsOfQuizz(Quizz quizz);
    public void deleteQuestion(Long quesId);

    public Question get(Long questionId);
}
