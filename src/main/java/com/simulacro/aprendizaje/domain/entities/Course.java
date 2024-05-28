package com.simulacro.aprendizaje.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = false)
    private List<Lesson> lessons;

     @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messagge> messages = new ArrayList<>();
  
}
