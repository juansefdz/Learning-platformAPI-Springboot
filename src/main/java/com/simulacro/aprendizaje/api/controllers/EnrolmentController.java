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
import com.simulacro.aprendizaje.api.dto.request.EnrollmentRequest;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IEnrrollmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
public class EnrolmentController {
     @Autowired
    private final IEnrrollmentService iEnrrollmentService;

    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iEnrrollmentService.getAll(page - 1, size, SortType.NONE));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.iEnrrollmentService.getById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<EnrollmentResponse> create(
            @Validated @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(this.iEnrrollmentService.create(request));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.iEnrrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update/{id}")

    public ResponseEntity<EnrollmentResponse> update(
            @Validated @RequestBody EnrollmentRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iEnrrollmentService.update(request, id));
    }

    
}
