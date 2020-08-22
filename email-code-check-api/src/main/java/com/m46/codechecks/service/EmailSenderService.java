package com.m46.codechecks.service;

import com.m46.codechecks.config.EmailCodeCheckProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailCodeCheckProps verificationProps;

    public void sendVerificationEmail(String email, String customerName, String code) {
        sendEmail(email, customerName, code, verificationProps.getEmailTemplateId());
    }

    public void sendEmail(String email, String customerName, String code, Long templateId) {
        //TODO
    }

}
