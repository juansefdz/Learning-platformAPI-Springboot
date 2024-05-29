package com.simulacro.aprendizaje.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.LessonRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse;
import com.simulacro.aprendizaje.domain.entities.Course;
import com.simulacro.aprendizaje.domain.entities.Enrrollment;
import com.simulacro.aprendizaje.domain.entities.Lesson;
import com.simulacro.aprendizaje.domain.repositories.LessonRepository;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ILessonService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.lessonRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public LessonResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        Lesson lesson = this.find(id);
        lesson = this.requestToEntity(request);
        lesson.setIdLesson(id);
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long id) {
        this.lessonRepository.delete(this.find(id));
    }

    private Lesson find(Long id) {
        return this.lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found with this ID: " + id));
    }

    private LessonResponse entityToResponse(Lesson lesson) {
        return LessonResponse.builder()
        .lessonTitle(lesson.getLessonTitle())
        .content(lesson.getContent())
        .courses(coursesToResponse(lesson.getCourse()))
        .assignments(null)
        .build();
    }

    private Lesson requestToEntity(LessonRequest request) {
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(request, lesson);
        return lesson;
    }


    private CourseResponse coursesToResponse(Course entity) {
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(entity, courseResponse);
        return courseResponse;

    }






}
