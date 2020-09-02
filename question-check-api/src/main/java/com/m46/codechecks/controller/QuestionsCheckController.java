package com.m46.codechecks.controller;

import com.m46.codechecks.model.dto.Question;
import com.m46.codechecks.model.dto.QuestionCheckRequest;
import com.m46.codechecks.model.dto.QuestionCheckStatus;
import com.m46.codechecks.service.VerificationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/home")
public class QuestionsCheckController {

    private final VerificationService verificationService;

    @GetMapping(path = "/question")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getQuestion(){
        System.out.println("Вход");
        return ResponseEntity.ok(verificationService.getAllQuestion());
    }

    @GetMapping(path = "/answer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getAnswer(@RequestParam(value = "id",required = false)Integer id,
                                    @RequestParam(value = "answer",required = false)String ans){
        System.out.println("Вход");
        return ResponseEntity.ok(verificationService.getQuestion(id,ans));
    }

}
