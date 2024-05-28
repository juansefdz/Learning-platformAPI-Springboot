package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Submission;


public interface SubmissionRepository extends JpaRepository <Submission, Long> {
    
}
