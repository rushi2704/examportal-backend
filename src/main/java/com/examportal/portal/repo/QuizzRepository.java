package com.examportal.portal.repo;

import com.examportal.portal.model.exam.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzRepository extends JpaRepository<Quizz, Long> {

}
