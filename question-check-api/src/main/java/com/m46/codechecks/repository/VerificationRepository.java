package com.m46.codechecks.repository;


import com.m46.codechecks.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VerificationRepository extends JpaRepository<QuestionEntity, Integer> {

    QuestionEntity findAllById(int id);
}
