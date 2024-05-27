package com.simulacro.aprendizaje.domain.entities;

import java.util.Date;

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

@Entity(name="assignment")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Assignment {

     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="assignment_id",length = 11)
    private Long idAssignment;

    @Column(name="assignment_title", length = 100, nullable=false )
    private String assignmentTitle;

    @Column(name="description")
    @Lob
    private String description;

    @Column (name="due_date")
    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_id_lesson", referencedColumnName = "lesson_id") 
    private Lesson lesson;
    
}