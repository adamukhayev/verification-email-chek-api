package com.m46.codechecks.controller;

import com.m46.codechecks.model.EmailVerification;
import com.m46.codechecks.model.EmailVerificationState;
import com.m46.codechecks.model.VerificationRequest;
import com.m46.codechecks.service.EmailVerificationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/email/verify")
public class EmailVerificationController {
    private final EmailVerificationService verificationService;

    @GetMapping(path = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "",
            notes = "Utility method to check current state of the email verification process.")
    public EmailVerificationState getVerificationState(
            @PathVariable String email
    ){

        return verificationService.getVerificationState(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "",
            notes = "Initial request for email verification. Sends verification code to provided email")
    public void requestEmailVerification(
            @Validated
            @RequestBody VerificationRequest verificationRequest
    ) {
        log.info("New verification for {} {}", verificationRequest.getFirstName(), verificationRequest.getEmail());
        verificationService.requestEmailVerification(
                verificationRequest.getEmail(),
                verificationRequest.getFirstName());
    }

    @PostMapping(path = "/check")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "",
            notes = "Used to verify email by comparing verification code to the one stored in DB.")
    public void verify(
            @Validated
            @RequestBody EmailVerification verification
    ) {
        log.info("Verify {} with code '{}'", verification.getEmail(), verification.getVerificationCode());
        verificationService.verify(verification.getEmail(), verification.getVerificationCode());
    }
}
