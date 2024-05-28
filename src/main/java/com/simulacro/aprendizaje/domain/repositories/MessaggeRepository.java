package com.simulacro.aprendizaje.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simulacro.aprendizaje.domain.entities.Messagge;

public interface MessaggeRepository extends JpaRepository<Messagge, Long> {

}
