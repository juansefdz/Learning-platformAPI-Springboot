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

@Entity(name="submission")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="submission_id",length = 11)
    private Long idSubmission;
    
    @Column(name="context")
    @Lob
    private String context;

    @Column(name="grade")
    private Double grade;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id") 
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_assignment_id", referencedColumnName = "assignment_id") 
    private Assignment assignment;



    
}
