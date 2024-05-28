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


@Entity(name="messagge")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class Messagge {

     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="course_id",length = 11)
    private Long idCourse;
    
    @Column(name="message_content" )
    @Lob
    private String description;

    @Column (name="sent_date")
    private Date sentDate;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_sender_id", referencedColumnName = "user_id") 
    private UserEntity sender;
    
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_receiver_id", referencedColumnName = "user_id") 
    private UserEntity receiver;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_course_id", referencedColumnName = "course_id") 
    private Course course;
    
}
