package com.m46.codechecks.model.dto;

import lombok.Data;

@Data
public class QuestionCheckRequest {

    private String questionId;
    private String answer;
}
