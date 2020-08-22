package com.m46.codechecks.service;

import com.m46.codechecks.model.EmailVerificationState;
import com.m46.codechecks.model.VerificationStatus;
import com.m46.codechecks.model.entity.EmailVerificationEntity;
import com.m46.codechecks.repository.EmailVerificationRepository;
import com.m46.codechecks.config.EmailCodeCheckProps;
import com.m46.codechecks.exceptions.VerificationCodeExpiredException;
import com.m46.codechecks.exceptions.WrongVerificationCodeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailVerificationServiceTest {
    public static final String TEST_EMAIL = "test@test.com";
    public static final String TEST_VERIFICATION_CODE = "123456";
    @Mock
    private EmailSenderService emailSender;
    @Mock
    private EmailVerificationRepository verificationRepository;
    @Mock
    private EmailCodeCheckProps properties;

    @InjectMocks
    private EmailVerificationService verificationService;

    @Test
    void requestEmailVerification() {
        verificationService.requestEmailVerification(TEST_EMAIL, "John");
    }

    @Test
    @DisplayName("correct verification code")
    void verifyCorrectCode() {
        when(verificationRepository.findByEmail(any(String.class))).thenReturn(mockVerificationEntity());
        when(properties.getValidityPeriod()).thenReturn(24);
        assertDoesNotThrow(() -> {
            verificationService.verify(TEST_EMAIL, TEST_VERIFICATION_CODE);
        });
    }

    @Test
    @DisplayName("incorrect verification code")
    void verifyIncorrectCode() {
        when(verificationRepository.findByEmail(any(String.class))).thenReturn(mockVerificationEntity());
        when(properties.getValidityPeriod()).thenReturn(24);
        assertThrows(WrongVerificationCodeException.class, () -> {
            verificationService.verify(TEST_EMAIL, "654321");
        });
    }

    @Test
    @DisplayName("expired verification code")
    void verifyExpiredCode() {
        var verificationEntity = mockVerificationEntity();
        verificationEntity.setCreatedAt(LocalDateTime.now().minusDays(10));
        when(verificationRepository.findByEmail(any(String.class))).thenReturn(verificationEntity);
        when(properties.getValidityPeriod()).thenReturn(24);
        assertThrows(VerificationCodeExpiredException.class, () -> {
            verificationService.verify(TEST_EMAIL, TEST_VERIFICATION_CODE);
        });
    }

    @Test
    void getVerificationState() {
        var verificationEntity = mockVerificationEntity();
        var email = verificationEntity.getEmail();
        var status = verificationEntity.getStatus();
        when(verificationRepository.findByEmail(any(String.class))).thenReturn(verificationEntity);

        assertEquals(
                new EmailVerificationState(email, status),
                verificationService.getVerificationState(TEST_EMAIL)
        );
    }

    private EmailVerificationEntity mockVerificationEntity() {
        EmailVerificationEntity verificationEntity = new EmailVerificationEntity();
        verificationEntity.setId(1L);
        verificationEntity.setCreatedAt(LocalDateTime.now());
        verificationEntity.setEmail(TEST_EMAIL);
        verificationEntity.setVerificationCode(TEST_VERIFICATION_CODE);
        verificationEntity.setStatus(VerificationStatus.SENT);
        verificationEntity.setMessage("200 OK");

        return verificationEntity;
    }
}
