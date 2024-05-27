package com.simulacro.aprendizaje.domain.entities;

import com.simulacro.aprendizaje.api.utils.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="userEntity")
 @Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long idUser;
    
    @Column(name="username",nullable = false,length = 50)
    private String userName;

    @Column(name="password", nullable=false,length=255)
    private String password;

    @Column(name = "email",nullable=false,length=100 )
    private String email;

    @Column(name="full_name",length = 100)
    private String fullName;

    @Column(nullable = false)
    @Enumerated (EnumType.STRING)
    private Role role;

    
}

