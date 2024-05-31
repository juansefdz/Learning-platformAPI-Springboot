package com.simulacro.aprendizaje.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IUserEntityService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Tag(name = "User Entity Controller") // SWAGGER
@Controller
public class UserEntityController {

    @Autowired
    private final IUserEntityService objIUserEntityService;

    @Operation(summary = "Get all users") // SWAGGER
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1") // SWAGGER
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10") // SWAGGER
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.objIUserEntityService.getAll(page - 1, size, SortType.NONE));
    }

    @Operation(summary = "Get user by ID") // SWAGGER
    @ApiResponse(responseCode = "200", description = "User found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    @ApiResponse(responseCode = "404", description = "User not found") // SWAGGER
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(
            @Parameter(description = "User ID") // SWAGGER
            @PathVariable Long id) {

        return ResponseEntity.ok(this.objIUserEntityService.getById(id));
    }

    @Operation(summary = "Create a new user") // SWAGGER
    @ApiResponse(responseCode = "200", description = "User created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> create(
            @Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.objIUserEntityService.create(request));
    }

    @Operation(summary = "Delete user by ID") // SWAGGER
    @ApiResponse(responseCode = "204", description = "User deleted")
    @ApiResponse(responseCode = "404", description = "User not found") // SWAGGER
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "User ID") // SWAGGER
            @PathVariable Long id) {
        this.objIUserEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user by ID") // SWAGGER
    @ApiResponse(responseCode = "200", description = "User updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
    })
    @ApiResponse(responseCode = "404", description = "User not found") // SWAGGER
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserResponse> update(
            @Validated @RequestBody UserRequest request,
            @Parameter(description = "User ID") // SWAGGER
            @PathVariable Long id) {
        return ResponseEntity.ok(this.objIUserEntityService.update(request, id));
    }
}
