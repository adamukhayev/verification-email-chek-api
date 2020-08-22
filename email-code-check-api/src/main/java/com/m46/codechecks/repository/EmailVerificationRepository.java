package com.m46.codechecks.repository;

import com.m46.codechecks.model.VerificationStatus;
import com.m46.codechecks.model.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationEntity, String> {
    EmailVerificationEntity findByEmail(String email);
    void deleteByEmail(String email);

    @Modifying
    @Query("update EmailVerificationEntity ev " +
            "set ev.status = :newStatus, " +
                "ev.message = :message " +
            "where ev.email = :email")
    void updateVerificationStatus(
            @Param("email")String email,
            @Param("newStatus") VerificationStatus newStatus,
            @Param("message")String message
            );


}
