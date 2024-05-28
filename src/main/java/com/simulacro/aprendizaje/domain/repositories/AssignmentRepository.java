package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Assignment;


public interface AssignmentRepository extends JpaRepository <Assignment, Long> {
    
}
