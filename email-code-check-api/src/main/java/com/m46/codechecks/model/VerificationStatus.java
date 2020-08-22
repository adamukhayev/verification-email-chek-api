package com.m46.codechecks.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VerificationStatus {
    SENT("SENT"),
    SEND_ERROR("SEND_ERROR");

    @JsonValue
    private final String value;

    VerificationStatus(String value) {
        this.value = value;
    }
}
