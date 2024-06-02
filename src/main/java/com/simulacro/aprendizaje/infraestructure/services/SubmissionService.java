package com.simulacro.aprendizaje.infraestructure.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.SubmissionRequest;

import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.AssignmentResponseInSubmission;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.UserResponseInSubmission;
import com.simulacro.aprendizaje.domain.entities.Assignment;

import com.simulacro.aprendizaje.domain.entities.Submission;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.repositories.SubmissionRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ISubmissionService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService implements ISubmissionService {

    @Autowired
    private final SubmissionRepository submisionRepository;

    @Override
    public Page<SubmissionResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.submisionRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public SubmissionResponse getById(Long id) {
        Submission submission = find(id);
        return entityToResponse(submission);
    }

     private Submission find(Long id) {
        return submisionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found with id: " + id));
    }

    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        Submission submision = this.requestToEntity(request);
        return this.entityToResponse(this.submisionRepository.save(submision));
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Long id) {
        Submission submission = this.find(id);
            if (request.getContent() != null) submission.setContent(request.getContent());
            if (request.getSubmissionDate() != null) submission.setSubmissionDate(request.getSubmissionDate());
            if (request.getGrade() != null) submission.setGrade(request.getGrade());
        
        return this.entityToResponse(this.submisionRepository.save(submission));
    }

    @Override
    public void delete(Long id) {
        this.submisionRepository.delete(this.find(id));
    }



    private Submission requestToEntity(SubmissionRequest request) {
        Submission submission = new Submission();
        BeanUtils.copyProperties(request, submission);
        return submission;
    }


    private SubmissionResponse entityToResponse(Submission submission) {
        SubmissionResponse submissionResponse = new SubmissionResponse();
        submissionResponse.setIdSubmission(submission.getIdSubmission());
        submissionResponse.setContent(submission.getContent());
        submissionResponse.setSubmissionDate(submission.getSubmissionDate());
        submissionResponse.setGrade(submission.getGrade());
        submissionResponse.setAssignments(assignmentResponseInSubmission(List.of(submission.getAssignment())));
        submissionResponse.setUsers(userResponseInSubmission(List.of(submission.getUser())));
        
        
    
        return submissionResponse;
    }


    private List<AssignmentResponseInSubmission> assignmentResponseInSubmission(List<Assignment> assignments) {
        return assignments.stream()
                .map(assignment -> {
                    AssignmentResponseInSubmission assignmentResponseInSubmission = new AssignmentResponseInSubmission();
                    assignmentResponseInSubmission.setIdAssignment(assignment.getIdAssignment());
                    assignmentResponseInSubmission.setAssignmentTitle(assignment.getAssignmentTitle());
                    assignmentResponseInSubmission.setDescription(assignment.getDescription());
                    assignmentResponseInSubmission.setDueDateAssignment(assignment.getDueDateAssignment());
                    assignmentResponseInSubmission.setIdLesson(assignment.getLesson().getIdLesson());
                    return assignmentResponseInSubmission;
                })
                .collect(Collectors.toList());
    }
    

    private List<UserResponseInSubmission> userResponseInSubmission(List<UserEntity> users) {
        return users.stream()
            .map(user -> {
                UserResponseInSubmission userResponseInSubmission = new UserResponseInSubmission();
                BeanUtils.copyProperties(user, userResponseInSubmission);
                return userResponseInSubmission;
            })
            .collect(Collectors.toList());
    }


}
