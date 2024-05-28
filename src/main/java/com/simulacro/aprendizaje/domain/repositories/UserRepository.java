package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
    
}
