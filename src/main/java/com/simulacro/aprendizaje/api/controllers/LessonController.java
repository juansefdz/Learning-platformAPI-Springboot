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

import com.simulacro.aprendizaje.api.dto.request.LessonRequest;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse.LessonResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ILessonService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {

    @Autowired
    private final ILessonService objILessonService;

    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.objILessonService.getAll(page - 1, size, SortType.NONE));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(this.objILessonService.getById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<LessonResponse> create(
            @Validated @RequestBody LessonRequest request) {
        return ResponseEntity.ok(this.objILessonService.create(request));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.objILessonService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<LessonResponse> update(
            @Validated @RequestBody LessonRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.objILessonService.update(request, id));
    }

}
