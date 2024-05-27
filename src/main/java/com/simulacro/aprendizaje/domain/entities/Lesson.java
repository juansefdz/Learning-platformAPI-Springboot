package com.simulacro.aprendizaje.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="lesson")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="lesson_id")
    private Long idLesson;

    @Column(name="lesson_title", length = 100, nullable=false )
    private String lessonTitle;

    @Column(name="content",length = 100 )
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id") 
    private Course course;



    
}
