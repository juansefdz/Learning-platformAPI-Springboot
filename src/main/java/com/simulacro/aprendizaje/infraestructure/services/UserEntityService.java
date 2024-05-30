package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse; // Añadir la importación de AssignmentResponse
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrollment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.entities.Message;
import com.simulacro.aprendizaje.domain.entities.Submission;
import com.simulacro.aprendizaje.domain.entities.Assignment; // Añadir la importación de Assignment
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

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final SubmissionRepository submissionRepository;

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

        userResponse.setEnrollments(enrollmentsToResponses(user.getEnrollments()));
        userResponse.setSentMessages(messagesToResponses(user.getSentMessages()));
        userResponse.setReceivedMessages(messagesToResponses(user.getReceivedMessages()));
        userResponse.setLessons(lessonsToResponse(user.getLessons()));
        userResponse.setSubmissions(submissionsToResponses(user.getSubmissions()));

        return userResponse;
    }

    private List<EnrollmentResponse> enrollmentsToResponses(List<Enrollment> enrollments) {
        return enrollments.stream()
                .map(enrollment -> {
                    EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
                    BeanUtils.copyProperties(enrollment, enrollmentResponse);
                    enrollmentResponse.setUser(userToResponse(enrollment.getUser()));
                    enrollmentResponse.setCourse(courseToResponse(enrollment.getCourse()));
                    return enrollmentResponse;
                })
                .collect(Collectors.toList());
    }

    private UserResponse userToResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    private CourseResponse courseToResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(course, courseResponse);
        return courseResponse;
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

    private List<LessonResponse> lessonsToResponse(List<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> {
                    LessonResponse lessonResponse = new LessonResponse();
                    BeanUtils.copyProperties(lesson, lessonResponse);

                    //lessonResponse.setCourse(courseToResponse(lesson.getCourse()));
                    //lessonResponse.setUsers(userToResponse(lesson.getUser()));
                    //lessonResponse.getAssignments(lesson.getAssignments());
                    return lessonResponse;
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
