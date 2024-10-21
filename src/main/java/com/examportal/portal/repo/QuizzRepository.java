package com.examportal.portal.repo;

import com.examportal.portal.model.exam.Category;
import com.examportal.portal.model.exam.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizzRepository extends JpaRepository<Quizz, Long> {

    public List<Quizz> findBycategory(Category category);

    public List<Quizz> findByActive(Boolean b);
    public  List<Quizz> findByCategoryAndActive(Category c, Boolean b);
}
