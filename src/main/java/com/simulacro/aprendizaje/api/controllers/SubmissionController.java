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

import com.simulacro.aprendizaje.api.dto.request.SubmissionRequest;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ISubmissionService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import com.simulacro.aprendizaje.utils.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/submissions")
@AllArgsConstructor
@Tag(name = "Submission Entity Controller", description = "Endpoints to handle submissions")
public class SubmissionController {

    @Autowired
    private final ISubmissionService iSubmissionService;

    /*----------------------------
     * GET ALL
     * ----------------------------
     */

    @Operation(
        summary = "Displays all Submissions",
        description = "Displays the Submissions in a list, it is configured to display 10 items per page."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Submission List"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the list of Submissions. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @GetMapping
    public ResponseEntity<Page<SubmissionResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iSubmissionService.getAll(page - 1, size, SortType.NONE));
    }

    /*------------------------------
     * GET BY ID
     * -----------------------------
     */

     @Operation(
        summary = "Displays one submission by id",
        description = "Show the submission by the ID sent or requested by path, value cannot be less than 1."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved submission"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the submission. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "Submission not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @GetMapping(path = "/{submission_id}")
    public ResponseEntity<SubmissionResponse> getById(
        @Parameter(description = "Assignment ID", example = "1")
        @PathVariable Long submission_id) {

         SubmissionResponse submission = iSubmissionService.getById(submission_id);
        if (submission == null) {
            throw new ResourceNotFoundException("Submission not found");
        }
        return ResponseEntity.ok(submission);
    }


    /*--------------------
     * CREATE SUBMISSION
     * -------------------
     */

     @Operation(
        summary = "Creates a new Submission",
        description = "Create a new Submission by entering the required data"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created Submission"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to create a Submission. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<SubmissionResponse> create(
            @Validated @RequestBody SubmissionRequest request) {
        return ResponseEntity.ok(this.iSubmissionService.create(request));
    }

     /*----------------------
     * DELETE SUBMISSION
     * ---------------------
     */

     @Operation(
        summary = "Delete Submission by ID",
        description = "Deletes a Submission based on an ID to be sent by Path, value cannot be less than 1"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted Submission"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to delete the Submission. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "Submission not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @DeleteMapping(path = "/{submission_id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "Submission ID", example = "1")
        @PathVariable Long id) {
        this.iSubmissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

     /*----------------------
     * UPDATE SUBMISSION
     * ---------------------
     */

     @Operation(
        summary = "Update Submission by ID",
        description = "Updates a previously created Submission and the ID and the new modified parameters must be sent through the Path, value cannot be less than 1"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated Submission"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to update the Submission. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "Submission not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @PutMapping(path = "/{submission_id}")
    public ResponseEntity<SubmissionResponse> update(
            @Validated @RequestBody SubmissionRequest request,
            @Parameter(description = "Submission ID", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iSubmissionService.update(request, id));
    }
}
