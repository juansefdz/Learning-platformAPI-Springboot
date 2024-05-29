package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrrollment;
import com.simulacro.aprendizaje.domain.entities.Message;
import com.simulacro.aprendizaje.domain.entities.Submission;
import com.simulacro.aprendizaje.domain.entities.UserEntity;
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
        if (page < 0)
            page = 0;

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
        
        userResponse.setCourse(CourseToResponse(entity.getCourses()));
        userResponse.setEnrollments(EnrollmentToResponse(entity.getEnrollments()));
        userResponse.setMessages(MessageToResponse(entity.getSentMessages()));  
        userResponse.setMessages(MessageToResponse(entity.getReceivedMessages()));
        userResponse.setSubmission(SubmissionToResponse(entity.getSubmissions()));
        return userResponse;
    }


    private UserEntity requestToEntity(UserRequest request) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);
        return user;
    }


    private CourseResponse CourseToResponse(List<Course> list) {
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(list, courseResponse);
        return courseResponse;
    }
    private EnrollmentResponse EnrollmentToResponse(List<Enrrollment> list) {
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
        BeanUtils.copyProperties(list, enrollmentResponse);
        return enrollmentResponse;
    }
    private MessageResponse MessageToResponse(List<Message> list) {
        MessageResponse messageresponse = new MessageResponse();
        BeanUtils.copyProperties(list, messageresponse);
        return messageresponse;
    }
    private SubmissionResponse SubmissionToResponse(List<Submission> list) {
        SubmissionResponse submissionResponse  = new SubmissionResponse();
        BeanUtils.copyProperties(list, submissionResponse);
        return submissionResponse;
    }




}
