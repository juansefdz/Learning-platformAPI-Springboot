package com.simulacro.aprendizaje.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simulacro.aprendizaje.api.dto.request.CourseRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ICourseService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final ICourseService iCourseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iCourseService.getAll(page - 1, size, SortType.NONE));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.iCourseService.getById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CourseResponse> create(
            @Validated @RequestBody CourseRequest request) {
        return ResponseEntity.ok(this.iCourseService.create(request));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.iCourseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update/{id}")

    public ResponseEntity<CourseResponse> update(
            @Validated @RequestBody CourseRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iCourseService.update(request, id));
    }

}
