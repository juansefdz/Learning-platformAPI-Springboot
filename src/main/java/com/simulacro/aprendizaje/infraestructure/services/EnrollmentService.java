package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simulacro.aprendizaje.api.dto.request.EnrollmentRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;
import com.simulacro.aprendizaje.domain.entities.Enrollment;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.repositories.EnrrollmentRepository;
import com.simulacro.aprendizaje.domain.repositories.UserRepository;
import com.simulacro.aprendizaje.domain.repositories.CourseRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IEnrrollmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnrollmentService implements IEnrrollmentService {

    @Autowired
    private EnrrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.enrollmentRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public EnrollmentResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        Enrollment enrollment = this.requestToEntity(request);
        return this.entityToResponse(this.enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponse update(EnrollmentRequest request, Long id) {
        Enrollment enrollment = this.find(id);
        enrollment = this.requestToEntity(request);
        enrollment.setIdEnrollment(id);
        return this.entityToResponse(this.enrollmentRepository.save(enrollment));
    }

    @Override
    public void delete(Long id) {
        this.enrollmentRepository.delete(this.find(id));
    }

    private Enrollment find(Long id) {
        return this.enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment ID not found with this ID: " + id));
    }

    private Enrollment requestToEntity(EnrollmentRequest request) {
        Enrollment enrollment = new Enrollment();

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + request.getCourseId()));

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(request.getEnrollmentDate());

        return enrollment;
    }

    private EnrollmentResponse entityToResponse(Enrollment enrollment) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(enrollment.getUser(), userResponse);
        userResponse.setCourses(null);
        userResponse.setEnrollments(null);
        userResponse.setSentMessages(null);
        userResponse.setReceivedMessages(null);
        userResponse.setLessons(null);
        userResponse.setSubmissions(null);

        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(enrollment.getCourse(), courseResponse);
        courseResponse.setEnrollments(null);
        courseResponse.setLessons(null);
        courseResponse.setMessages(null);

        return EnrollmentResponse.builder()
                .idEnrollment(enrollment.getIdEnrollment())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .user(userResponse)
                .course(courseResponse)
                .build();
    }
}
