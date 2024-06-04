package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.simulacro.aprendizaje.api.dto.request.AssignmentRequest;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.LessonsResponseInAssignment;
import com.simulacro.aprendizaje.domain.entities.Assignment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.repositories.AssignmentRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IAssignmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public Page<AssignmentResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return assignmentRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public AssignmentResponse getById(Long id) {
        return entityToResponse(find(id));
    }

    private Assignment find(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with ID: " + id));
    }

    @Override
    public AssignmentResponse create(AssignmentRequest request) {
        Assignment assignment = requestToEntity(request);
        return entityToResponse(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentResponse update(AssignmentRequest request, Long id) {
        Assignment assignment = find(id);

        if (request.getAssignmentTitle() != null) {
            assignment.setAssignmentTitle(request.getAssignmentTitle());
        }

        if (request.getDescription() != null) {
            assignment.setDescription(request.getDescription());
        }

        if (request.getDueDateAssignment() != null) {
            assignment.setDueDateAssignment(request.getDueDateAssignment());
        }

        return entityToResponse(assignmentRepository.save(assignment));
    }

    @Override
    public void delete(Long id) {
        assignmentRepository.delete(find(id));
    }

    private AssignmentResponse entityToResponse(Assignment assignment) {
        return AssignmentResponse.builder()
                .idAssignment(assignment.getIdAssignment())
                .assignmentTitle(assignment.getAssignmentTitle())
                .description(assignment.getDescription())
                .dueDateAssignment(assignment.getDueDateAssignment())
                .lessons(lessonsResponseInAssignments(List.of(assignment.getLesson())))
                .build();
    }

    private Assignment requestToEntity(AssignmentRequest request) {
        return Assignment.builder()
                .assignmentTitle(request.getAssignmentTitle())
                .description(request.getDescription())
                .dueDateAssignment(request.getDueDateAssignment())
                .build();
    }

    private List<LessonsResponseInAssignment> lessonsResponseInAssignments(List<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> LessonsResponseInAssignment.builder()
                        .lessonId(lesson.getIdLesson())
                        .lessonTitle(lesson.getLessonTitle())
                        .content(lesson.getContent())
                        .courseId(lesson.getCourse() != null ? lesson.getCourse().getIdCourse() : null)
                        .build())
                .collect(Collectors.toList());
    }
}
