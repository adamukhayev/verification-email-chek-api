package com.m46.codechecks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerification {
    @Email
    private String email;
    private String verificationCode;
}
