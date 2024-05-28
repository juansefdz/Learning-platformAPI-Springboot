package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Course;


public interface CourseRepository extends JpaRepository <Course, Long> {
    
}
