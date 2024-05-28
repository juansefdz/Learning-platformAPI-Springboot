package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Enrrollment;

public interface EnrrollmentRepository extends JpaRepository <Enrrollment, Long> {
    
}
