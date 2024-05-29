package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.*;
import com.simulacro.aprendizaje.domain.entities.*;
import com.simulacro.aprendizaje.domain.repositories.UserRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IUserEntityService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserEntityService implements IUserEntityService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public UserResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public UserResponse create(UserRequest request) {
        UserEntity user = this.requestToEntity(request);
        return this.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        UserEntity user = this.find(id);
        user = this.requestToEntity(request);
        user.setIdUser(id);
        return this.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        this.userRepository.delete(this.find(id));
    }

    private UserEntity find(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found with this ID: " + id));
    }

    private UserResponse entityToResponse(UserEntity entity) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(entity, userResponse);
        
        userResponse.setCourses(coursesToResponses(entity.getCourses()));
        userResponse.setEnrollments(enrollmentsToResponses(entity.getEnrollments()));
        userResponse.setSentMessages(messagesToResponses(entity.getSentMessages()));
        userResponse.setReceivedMessages(messagesToResponses(entity.getReceivedMessages()));
        userResponse.setSubmissions(submissionsToResponses(entity.getSubmissions()));
        return userResponse;
    }

    private UserEntity requestToEntity(UserRequest request) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    private List<CourseResponse> coursesToResponses(List<Course> courses) {
        return courses.stream()
                      .map(course -> {
                          CourseResponse courseResponse = new CourseResponse();
                          BeanUtils.copyProperties(course, courseResponse);
                          return courseResponse;
                      })
                      .collect(Collectors.toList());
    }
    /* 
    CASO CON BUILDER
    private List<CourseResponse> coursesToResponses(List<Course> courses) {
        return courses.stream()
                      .map(course -> CourseResponse.builder()
                                                  .courseName(course.getCourseName())
                                                  .description(course.getDescription())
                                                  .build())
                      .collect(Collectors.toList());
    } */
    
    private List<EnrollmentResponse> enrollmentsToResponses(List<Enrrollment> enrollments) {
        return enrollments.stream()
                          .map(enrollment -> {
                              EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
                              BeanUtils.copyProperties(enrollment, enrollmentResponse);
                              return enrollmentResponse;
                          })
                          .collect(Collectors.toList());
    }

    private List<MessageResponse> messagesToResponses(List<Message> messages) {
        return messages.stream()
                       .map(message -> {
                           MessageResponse messageResponse = new MessageResponse();
                           BeanUtils.copyProperties(message, messageResponse);
                           return messageResponse;
                       })
                       .collect(Collectors.toList());
    }

    private List<SubmissionResponse> submissionsToResponses(List<Submission> submissions) {
        return submissions.stream()
                          .map(submission -> {
                              SubmissionResponse submissionResponse = new SubmissionResponse();
                              BeanUtils.copyProperties(submission, submissionResponse);
                              return submissionResponse;
                          })
                          .collect(Collectors.toList());
    }
}
