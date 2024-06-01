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
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.EnrollmentResponseInCourse;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.LessonResponseIncourse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrollment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.entities.Message;
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
        if (page < 0) page = 0;

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

    private CourseResponse entityToResponse(Course course) {
        return CourseResponse.builder()
                .idCourse(course.getIdCourse())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .idInstructor(course.getInstructor().getIdUser())
                .nameInstructor(course.getInstructor().getFullName())
                .enrollments(enrollmentResponseInCourse(course.getEnrollments()))
                .lessons(lessonResponseIncourse(course.getLessons()))
                .messages(messagesToResponses(course.getMessages()))  
                .build();
    }

    private Course requestToEntity(CourseRequest request) {
        UserEntity instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Instructor not found with ID: " + request.getInstructorId()));
    
        Course course = Course.builder()
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .build();
    
        course.setInstructor(instructor); 
    
        return course;
    }
    


    private List<LessonResponseIncourse> lessonResponseIncourse(List<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> {
                    LessonResponseIncourse lessonResponseIncourse = new LessonResponseIncourse();
                    BeanUtils.copyProperties(lesson, lessonResponseIncourse);
                    return lessonResponseIncourse;
                })
                .collect(Collectors.toList());
    }

    private List<EnrollmentResponseInCourse> enrollmentResponseInCourse(List<Enrollment> enrollments) {
        return enrollments.stream()
                .map(enrollment -> {
                    EnrollmentResponseInCourse enrollmentResponseInCourse = new EnrollmentResponseInCourse();
                    enrollmentResponseInCourse.setIdEnrollment(enrollment.getIdEnrollment());
                    enrollmentResponseInCourse.setEnrollmentDate(enrollment.getEnrollmentDate());
                    return enrollmentResponseInCourse;
                })
                .collect(Collectors.toList());
    }

    private List<MessageResponse> messagesToResponses(List<Message> messages) {
        return messages.stream()
                .map(message -> {
                    MessageResponse messageResponse = new MessageResponse();
                    BeanUtils.copyProperties(message, messageResponse);
                    messageResponse.setMessageId(message.getIdMessage());
                    messageResponse.setSenderId(message.getSender().getIdUser());
                    messageResponse.setReceiverId(message.getReceiver().getIdUser());
                    
                    
                    if (message.getCourse() != null) {
                        messageResponse.setCourseId(message.getCourse().getIdCourse());
                    } else {
                        messageResponse.setCourseId(null); 
                    }
                    
                    messageResponse.setDate(message.getSentDate());
                    return messageResponse;
                })
                .collect(Collectors.toList());
    }
    

   
    

    
    
   
}
