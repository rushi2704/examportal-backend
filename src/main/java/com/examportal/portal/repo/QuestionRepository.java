package com.examportal.portal.repo;

import com.examportal.portal.model.exam.Question;
import com.examportal.portal.model.exam.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuizz(Quizz quizz);
}
