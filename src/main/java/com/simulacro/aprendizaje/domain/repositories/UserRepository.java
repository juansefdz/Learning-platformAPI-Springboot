package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simulacro.aprendizaje.domain.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long>{
    
}
