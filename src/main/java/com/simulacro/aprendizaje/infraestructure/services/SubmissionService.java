package com.simulacro.aprendizaje.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.SubmissionRequest;

import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;

import com.simulacro.aprendizaje.domain.entities.Submission;

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
        if (page < 0)
            page = 0;
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
        if (request.getContent() != null)
            submission.setContent(request.getContent());

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

        if (submission.getAssignment() != null) {
            submissionResponse.setIdAssignment(submission.getAssignment().getIdAssignment());
        } else {

            submissionResponse.setIdAssignment(null);
        }

        if (submission.getUser() != null) {
            submissionResponse.setIdUser(submission.getUser().getIdUser());
        } else {

            submissionResponse.setIdUser(null);
        }

        return submissionResponse;
    }

}