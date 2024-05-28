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
import com.simulacro.aprendizaje.domain.repositories.CourseRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ICourseService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseService implements ICourseService{

    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public Page<CourseResponse> getAll(int page, int size, SortType sortType) {
        if (page <0)
        page = 0;

        PageRequest pagination = PageRequest.of(page,size);
        return this.courseRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public CourseResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = this.requestToEntity(request);
        course.setEnrollments(new ArrayList<>());
        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public CourseResponse update(CourseRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }



      
    private Course find(Long id) {
    return this.courseRepository.findById(id).orElseThrow(() -> new RuntimeException("User ID not found with this ID: " + id));
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
        return Course.builder()
            .courseName(null)
            .description(null)
            .instructor(null)
            .build();
    }

    private EnrollmentResponse enrollmentToResponse (Enrrollment entity){
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
        BeanUtils.copyProperties(entity, enrollmentResponse);
        return enrollmentResponse;

    }
    private LessonResponse lessonToResponse (Lesson entity){
        LessonResponse lessonResponse = new LessonResponse();
        BeanUtils.copyProperties(entity, lessonResponse);
        return lessonResponse;
        
    }

    private MessaggeResponse messageToResponse (Messagge entity){
        MessaggeResponse messaggeResponse = new MessaggeResponse();
        BeanUtils.copyProperties(entity, messaggeResponse);
        return messaggeResponse;
        
    }


    
}
