package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.CourseResponseInUser;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.EnrollmentResponseInUser;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.LessonResponseInUser;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.SubmissionResponseInUser;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrollment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.entities.Message;
import com.simulacro.aprendizaje.domain.entities.Submission;
import com.simulacro.aprendizaje.domain.entities.Assignment; 
import com.simulacro.aprendizaje.domain.entities.UserEntity;
import com.simulacro.aprendizaje.domain.repositories.CourseRepository;
import com.simulacro.aprendizaje.domain.repositories.LessonRepository;
import com.simulacro.aprendizaje.domain.repositories.MessageRepository;
import com.simulacro.aprendizaje.domain.repositories.SubmissionRepository;
import com.simulacro.aprendizaje.domain.repositories.UserRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IUserEntityService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public UserResponse getById(Long id) {
        UserEntity user = find(id);
        return entityToResponse(user);
    }

    private UserEntity find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    private UserResponse entityToResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        
        userResponse.setCourses(user.getCourses().stream()
            .map(this::courseToResponse)
            .collect(Collectors.toList()));

        userResponse.setEnrollments(enrollmentResponseInUser(user.getEnrollments()));
        userResponse.setLessons(lessonsToResponse(user.getLessons()));
        userResponse.setSubmissions(submissionsToResponses(user.getSubmissions()));
        userResponse.setSentMessages(messagesToResponses(user.getSentMessages()));
        userResponse.setReceivedMessages(messagesToResponses(user.getReceivedMessages()));
        

        return userResponse;
    }

    private List<EnrollmentResponseInUser> enrollmentResponseInUser(List<Enrollment> enrollments) {
        return enrollments.stream()
                .map(enrollment -> {
                    EnrollmentResponseInUser enrollmentResponseInUser = new EnrollmentResponseInUser();
                    
                    enrollmentResponseInUser.setIdEnrollment(enrollment.getIdEnrollment());
                    enrollmentResponseInUser.setEnrollmentDate(enrollment.getEnrollmentDate());
        
                    return enrollmentResponseInUser;
                })
                .collect(Collectors.toList());
    }

    private UserResponse userToResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    private CourseResponseInUser courseToResponse(Course course) {
        CourseResponseInUser courseResponseInUser = new CourseResponseInUser();
        courseResponseInUser.setIdCourse(course.getIdCourse());
        courseResponseInUser.setCourseName(course.getCourseName());
        courseResponseInUser.setDescription(course.getDescription());
        courseResponseInUser.setIdInstructor(course.getInstructor().getIdUser());
        return courseResponseInUser;
    }

    private List<MessageResponse> messagesToResponses(List<Message> messages) {
        return messages.stream()
                .map(message -> {
                    MessageResponse messageResponse = new MessageResponse();
                    BeanUtils.copyProperties(message, messageResponse);
                    messageResponse.setMessageId(message.getIdMessage());
                    messageResponse.setSenderId(message.getSender().getIdUser());
                    messageResponse.setReceiverId(message.getReceiver().getIdUser());
                    messageResponse.setCourseId(message.getCourse().getIdCourse());
                    messageResponse.setDate(message.getSentDate());
                    return messageResponse;
                })
                .collect(Collectors.toList());
    }

    private List<LessonResponseInUser> lessonsToResponse(List<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> {
                    LessonResponseInUser lessonResponseInUser = new LessonResponseInUser();
                    BeanUtils.copyProperties(lesson, lessonResponseInUser);
                    return lessonResponseInUser;
                })
                .collect(Collectors.toList());
    }

    private List<SubmissionResponseInUser> submissionsToResponses(List<Submission> submissions) {
        return submissions.stream()
                .map(submission -> {
                    SubmissionResponseInUser submissionResponseInUser = new SubmissionResponseInUser();
                    BeanUtils.copyProperties(submission, submissionResponseInUser);
                
                    return submissionResponseInUser;
                })
                .collect(Collectors.toList());
    }

    private AssignmentResponse assignmentToResponse(Assignment assignment) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        BeanUtils.copyProperties(assignment, assignmentResponse);
        return assignmentResponse;
    }

    private UserEntity requestToEntity(UserRequest request) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
