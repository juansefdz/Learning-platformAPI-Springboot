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


import com.simulacro.aprendizaje.api.dto.request.MessageRequest;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessageService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/messages")
@AllArgsConstructor
public class MessageController {

    private final ImessageService objImessageService;

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(objImessageService.getAll(page - 1, size, SortType.NONE));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(objImessageService.getById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<MessageResponse> create(
            @Validated @RequestBody MessageRequest request) {
        return ResponseEntity.ok(objImessageService.create(request));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objImessageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<MessageResponse> update(
            @Validated @RequestBody MessageRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(objImessageService.update(request, id));
    }
}
