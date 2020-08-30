package com.m46.codechecks.service;

import com.m46.codechecks.exceptions.NoSuchEmailException;
import com.m46.codechecks.model.EmailVerificationState;
import com.m46.codechecks.model.VerificationStatus;
import com.m46.codechecks.model.entity.EmailVerificationEntity;
import com.m46.codechecks.repository.EmailVerificationRepository;
import com.m46.codechecks.config.EmailCodeCheckProps;
import com.m46.codechecks.exceptions.VerificationCodeExpiredException;
import com.m46.codechecks.exceptions.WrongVerificationCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailSenderService emailSender;
    private final EmailVerificationRepository verificationRepository;
    private final EmailCodeCheckProps properties;

    @Transactional
    public void requestEmailVerification(
            @NotNull @Email String email,
            String customerName
    ) {

        var verificationCode = generateCode();

        var verificationRequest = verificationRepository.findByEmail(email);

        if (verificationRequest == null) {
            verificationRequest = new EmailVerificationEntity();
            verificationRequest.setEmail(email);
        }
        verificationRequest.setCreatedAt(LocalDateTime.now());
        verificationRequest.setVerificationCode(verificationCode);
        verificationRepository.save(verificationRequest);
        try {
            emailSender.sendVerificationEmail(verificationRequest.getEmail(), customerName, verificationCode);
            verificationRepository.updateVerificationStatus(email, VerificationStatus.SENT, "200 OK");
        } catch (Exception e) {
            log.error("Error handling {} verification request. {}", email, e.getMessage());
            verificationRepository.updateVerificationStatus(email, VerificationStatus.SEND_ERROR, e.getMessage());
        }
    }

    @Transactional
    public void verify(
            @NotNull @Email String email,
            @NotNull String verificationCode
    ) {
        var verification = verificationRepository.findByEmail(email);
        if (verification == null) {
            throw new NoSuchEmailException(email);
        } else {
            if (notExpired(verification)) {
                if (verificationCode.equals(verification.getVerificationCode())) {
                    log.info("Successfully verified {}. Record will be deleted", email);
                    verificationRepository.deleteByEmail(email);
                } else {
                    log.warn("Wrong verification code '{}' for {}", verificationCode, email);
                    throw new WrongVerificationCodeException(verificationCode);
                }
            } else {
                log.warn("Verification code for {} expired", email);
                throw new VerificationCodeExpiredException();
            }
        }
    }

    public EmailVerificationState getVerificationState(String email) {
        var entity = verificationRepository.findByEmail(email);
        return new EmailVerificationState(entity.getEmail(), entity.getStatus());
    }

    private boolean notExpired(EmailVerificationEntity verification) {
        var validityPeriodInHours = properties.getValidityPeriod();
        return verification.getCreatedAt()
                .plusHours(validityPeriodInHours)
                .isAfter(LocalDateTime.now());
    }

    private String generateCode() {
        return RandomStringUtils.randomNumeric(properties.getCodeLength());
    }
}
