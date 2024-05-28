package com.simulacro.aprendizaje.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.simulacro.aprendizaje.utils.enums.SortType;


public interface CrudService <REQUEST,RESPONSE,TYPE> {
    Page<RESPONSE> getAll(int page, int size, SortType sortType);

    RESPONSE getById(TYPE id);

    RESPONSE create(REQUEST request);

    RESPONSE update(REQUEST request, TYPE id);

    void delete(TYPE id);

    
}
