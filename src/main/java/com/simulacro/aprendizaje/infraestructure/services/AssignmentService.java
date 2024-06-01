package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.simulacro.aprendizaje.api.dto.request.AssignmentRequest;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.domain.entities.Assignment;
import com.simulacro.aprendizaje.domain.repositories.AssignmentRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IAssignmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Override
    public Page<AssignmentResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.assignmentRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public AssignmentResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private Assignment find(Long id) {
        return this.assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment ID not found with this ID: " + id));
    }

    @Override
    public AssignmentResponse create(AssignmentRequest request) {
        Assignment assignment = this.requestToEntity(request);
        return this.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentResponse update(AssignmentRequest request, Long id) {
        Assignment assignment = this.find(id);
        assignment = this.requestToEntity(request);
        assignment.setIdAssignment(id);
        return this.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public void delete(Long id) {
        this.assignmentRepository.delete(this.find(id));
    }

    private AssignmentResponse entityToResponse(Assignment assignment) {
        return AssignmentResponse.builder()
                .build();
    }

    private Assignment requestToEntity(AssignmentRequest request) {
        Assignment assignment = Assignment.builder()
                .build();

        return assignment;
    }

}
