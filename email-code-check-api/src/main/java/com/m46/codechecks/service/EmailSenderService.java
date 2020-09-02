package com.m46.codechecks.service;

import com.m46.codechecks.config.EmailCodeCheckProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailCodeCheckProps verificationProps;

    private final JavaMailSender emailSender;

    public void sendVerificationEmail(String email, String customerName, String code) {
        sendEmail(email, customerName, code, verificationProps.getEmailTemplateId());
    }

    public void sendEmail(String email, String customerName, String code, Long templateId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ukhaev.adam777@gmail.com");
        message.setTo(email);
        message.setSubject("OK GOOGLE");
        message.setText("Ассаламу алейкум РЫжий красавчик "+code);
        emailSender.send(message);
    }

}
