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


@Entity(name="course")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="course_id",length = 11)
    private Long idCourse;

    @Column(name="course_name", length = 100, nullable=false )
    private String courseName;

    @Column(name="description" )
    @Lob
    private String description;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="fk_id_instructor", referencedColumnName="user_id") 
    private UserEntity instructor;
  
}
