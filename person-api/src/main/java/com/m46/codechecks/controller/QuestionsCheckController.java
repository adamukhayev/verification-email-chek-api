package com.m46.codechecks.controller;

import com.m46.codechecks.model.dto.Question;
import com.m46.codechecks.model.dto.QuestionCheckRequest;
import com.m46.codechecks.model.dto.QuestionCheckStatus;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class QuestionsCheckController {

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "",
            notes = "")
    public Question get() {

        return null;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "",
            notes = "")
    public QuestionCheckStatus check(@RequestBody QuestionCheckRequest questionCheckRequest) {

        return null;
    }
}
