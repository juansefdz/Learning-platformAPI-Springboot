package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.data.domain.Page;

import com.simulacro.aprendizaje.api.dto.request.MessaggeRequest;
import com.simulacro.aprendizaje.api.dto.response.MessaggeResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessaggeService;
import com.simulacro.aprendizaje.utils.enums.SortType;

public class MessaggeService implements ImessaggeService{

    @Override
    public Page<MessaggeResponse> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public MessaggeResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public MessaggeResponse create(MessaggeRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public MessaggeResponse update(MessaggeRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
