package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.data.domain.Page;

import com.simulacro.aprendizaje.api.dto.request.SubmissionRequest;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ISubmissionService;
import com.simulacro.aprendizaje.utils.enums.SortType;

public class SubmissionService implements ISubmissionService {

    @Override
    public Page<SubmissionResponse> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SubmissionResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
