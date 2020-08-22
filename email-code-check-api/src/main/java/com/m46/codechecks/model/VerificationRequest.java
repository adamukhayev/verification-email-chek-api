package com.m46.codechecks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
    @Email
    @NotNull
    private String email;
    private String firstName;
}
