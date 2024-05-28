package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.data.domain.Page;

import com.simulacro.aprendizaje.api.dto.request.EnrollmentRequest;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IEnrrollmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;

public class EnrollmentSerive implements IEnrrollmentService {

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public EnrollmentResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public EnrollmentResponse update(EnrollmentRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
