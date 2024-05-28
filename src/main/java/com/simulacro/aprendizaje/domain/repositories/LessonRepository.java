package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Lesson;


public interface LessonRepository  extends JpaRepository <Lesson, Long>{
    
}
