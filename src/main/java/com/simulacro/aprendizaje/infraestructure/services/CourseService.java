package com.simulacro.aprendizaje.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.simulacro.aprendizaje.api.dto.request.CourseRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessaggeResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrrollment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.entities.Messagge;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.repositories.CourseRepository;
import com.simulacro.aprendizaje.domain.repositories.UserRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ICourseService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<CourseResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.courseRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public CourseResponse getById(Long id) {
        return this.entityToResponse(this.find(id));

    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = this.requestToEntity(request);
        course.setEnrollments(new ArrayList<>());
        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public CourseResponse update(CourseRequest request, Long id) {
        Course course = this.find(id);
        course = this.requestToEntity(request);
        course.setIdCourse(id);
        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        this.courseRepository.delete(this.find(id));
    }

    private Course find(Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("course ID not found with this ID: " + id));
    }

    private CourseResponse entityToResponse(Course entity) {
        List<EnrollmentResponse> enrollmentResponses = entity.getEnrollments().stream()
                .map(this::enrollmentToResponse)
                .collect(Collectors.toList());

        List<LessonResponse> lessonResponses = entity.getLessons().stream()
                .map(this::lessonToResponse)
                .collect(Collectors.toList());

        List<MessaggeResponse> messageResponses = entity.getMessages().stream()
                .map(this::messageToResponse)
                .collect(Collectors.toList());

        return CourseResponse.builder()
                .idCourse(entity.getIdCourse())
                .courseName(entity.getCourseName())
                .description(entity.getDescription())
                .instructorId(entity.getInstructor().getIdUser())
                .enrollments(enrollmentResponses)
                .lessons(lessonResponses)
                .messages(messageResponses)
                .build();
    }

    private Course requestToEntity(CourseRequest request) {

        UserEntity instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Instructor not found with ID: " + request.getInstructorId()));

        return Course.builder()
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .instructor(instructor)
                .build();
    }

    private EnrollmentResponse enrollmentToResponse(Enrrollment entity) {
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
        BeanUtils.copyProperties(entity, enrollmentResponse);
        return enrollmentResponse;

    }

    private LessonResponse lessonToResponse(Lesson entity) {
        LessonResponse lessonResponse = new LessonResponse();
        BeanUtils.copyProperties(entity, lessonResponse);
        return lessonResponse;

    }

    private MessaggeResponse messageToResponse(Messagge entity) {
        MessaggeResponse messaggeResponse = new MessaggeResponse();
        BeanUtils.copyProperties(entity, messaggeResponse);
        return messaggeResponse;

    }

}
