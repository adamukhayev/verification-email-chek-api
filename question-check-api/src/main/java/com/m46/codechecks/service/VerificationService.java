package com.m46.codechecks.service;


import com.m46.codechecks.model.entity.QuestionEntity;
import com.m46.codechecks.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationService {

    private final VerificationRepository verificationRepository;

    public Boolean getQuestion(int id,String ans) {
        QuestionEntity questionEntity = verificationRepository.findAllById(id);

        if(!questionEntity.getAnswer().equals(ans)) {
            return false;
        }
        return true;
    }

    public QuestionEntity getAllQuestion() {
        List<QuestionEntity> list = verificationRepository.findAll();
        System.out.println("Массив ответов : "+list.toString());
        if(list.size() >= 0) {
            QuestionEntity question = list.get((int) (1+Math.random() * list.size()-1));
            return question;
        }
        return null;
    }
}
