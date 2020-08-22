package com.m46.codechecks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to send email")
public class EmailSendException extends RuntimeException {
    public EmailSendException(String message) {
        super(message);
    }
}
