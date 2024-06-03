package com.simulacro.aprendizaje.api.controllers;

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

import com.simulacro.aprendizaje.api.dto.request.AssignmentRequest;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IAssignmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import com.simulacro.aprendizaje.utils.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/assignments")
@AllArgsConstructor
@Tag(name = "Assignment Entity Controller", description = "Endpoints to handle assignments")
public class AssignmentController {

    private final IAssignmentService iAssignmentService;

    @Operation(summary = "Displays all Assignments", description = "Displays the Assignments in a list, it is configured to display 10 items per page.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<AssignmentResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(iAssignmentService.getAll(page - 1, size, SortType.NONE));
    }

    @Operation(summary = "Displays one assignment by id", description = "Show the assignment by the ID sent or requested by path, value cannot be less than 1.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{assignment_id}")
    public ResponseEntity<AssignmentResponse> getById(
            @Parameter(description = "Assignment ID", example = "1") @PathVariable Long assignment_id) {

        AssignmentResponse assignment = iAssignmentService.getById(assignment_id);
        if (assignment == null) {
            throw new ResourceNotFoundException("Assignment not found");
        }
        return ResponseEntity.ok(assignment);
    }

    @Operation(summary = "Creates a new Assignment", description = "Create a new Assignment by entering the required data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<AssignmentResponse> create(
            @Validated @RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(iAssignmentService.create(request));
    }

    @Operation(summary = "Delete Assignment by ID", description = "Deletes an Assignment based on an ID to be sent by Path, value cannot be less than 1")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping(path = "/{assignment_id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Assignment ID", example = "1") @PathVariable Long assignment_id) {
        iAssignmentService.delete(assignment_id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update Assignment by ID", description = "Updates a previously created Assignment and the ID and the new modified parameters must be sent through the Path, value cannot be less than 1")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(path = "/{assignment_id}")
    public ResponseEntity<AssignmentResponse> update(
            @Validated @RequestBody AssignmentRequest request,
            @Parameter(description = "Assignment ID", example = "1") @PathVariable Long assignment_id) {
        return ResponseEntity.ok(iAssignmentService.update(request, assignment_id));
    }
}
