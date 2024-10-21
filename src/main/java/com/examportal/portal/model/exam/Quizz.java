package com.examportal.portal.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity

public class Quizz  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;

    private String title;

    private String description;

    private String maxMarks;

    private String numberQuestions;

    private boolean  active = false;

    @OneToMany(mappedBy = "quizz",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category; 

    public Quizz() {

    }

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(String numberQuestions) {
        this.numberQuestions = numberQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
