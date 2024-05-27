package com.simulacro.aprendizaje.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="enrrollment")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Enrrollment {

     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="enrollment_id",length = 11)
    private Long idEnrollment;

    @Column (name="enrollment_date")
    private Date enrrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id") 
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id") 
    private Course course;
    
}