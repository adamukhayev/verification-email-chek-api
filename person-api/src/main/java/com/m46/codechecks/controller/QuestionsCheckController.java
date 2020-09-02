package com.m46.codechecks.controller;

import com.m46.codechecks.model.dto.EmailVerifiCodeDto;
import com.m46.codechecks.model.dto.PersonDto;

import com.m46.codechecks.service.PersonService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/add")
public class QuestionsCheckController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "",
            notes = "")
    public void check(@RequestBody PersonDto personDto) {
        System.out.println("Зашел");
        EmailVerifiCodeDto question = new EmailVerifiCodeDto();
        question.setEmail(personDto.getEmail());
        question.setFirstName(personDto.getName());

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/api/email/verify";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmailVerifiCodeDto> entity = new HttpEntity<>(question,headers);
        restTemplate.postForObject(url, entity, String.class);
        personService.addPerson(personDto);

    }
}
