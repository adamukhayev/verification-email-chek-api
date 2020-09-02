package com.m46.codechecks.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "capcha_tabels")
public class QuestionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;
}
