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
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse.EnrollmentResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IEnrrollmentService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
@Tag(name = "User Entity Controller") // SWAGGER
public class EnrolmentController {
     @Autowired
    private final IEnrrollmentService iEnrrollmentService;

    
    /*----------------------------
     * GET ALL
     * ----------------------------
     */

    @Operation(
        summary = "Displays all enrolments",
        description = "Displays the enrolments in a list, it is configured to display 10 items per page. "
    ) // SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved enrolment List"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the list of enrolment. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })// SWAGGER
    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1") // SWAGGER
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10") // SWAGGER
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iEnrrollmentService.getAll(page - 1, size, SortType.NONE));
    }


    /*------------------------------
     * GET BY ID
     * -----------------------------
     */

     @Operation(
        summary = "Displays one enrolment by id",
        description = "Shows the enrolment by the ID sent or requested by path,value cannot be less than 1."
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved enrolment"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })// SWAGGER
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> getById(
        @Parameter(description = "enrolment ID",example = "1") // SWAGGER
        @PathVariable Long id) {
        return ResponseEntity.ok(this.iEnrrollmentService.getById(id));
    }

    /*--------------------
     * CREATE ENROLMENT
     * -------------------
     */

     @Operation(
        summary = "creates a new enrolment",
        description = "create a new enrolment by entering the required data"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created enrolment"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to create a user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })//SWAGGER
    @PostMapping(path = "/create")
    public ResponseEntity<EnrollmentResponse> create(
            @Validated @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(this.iEnrrollmentService.create(request));
    }

     /*----------------------
     * DELETE ENROLMENT
     * ---------------------
     */

     @Operation(
        summary = "Delete enrolment by ID",
        description = "deletes an enrolment based on an ID to be sent by Path,value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted enrolment"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to delete the enrolment. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    }) //SWAGGER
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "enrolment ID",example = "1") // SWAGGER
        @PathVariable Long id) {
        this.iEnrrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*----------------------
     * UPDATE ENOLMENT
     * ---------------------
     */

     @Operation(
        summary = "update  enrolment by ID",
        description = "updates a previously created enrolment and the ID and the new modified parameters must be sent through the Path, value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated enrolment"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to update the enrolment. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    }) //SWAGGER
    @PutMapping(path = "/update/{id}")

    public ResponseEntity<EnrollmentResponse> update(
            @Validated @RequestBody EnrollmentRequest request,
            @Parameter(description = "enrolment ID",example = "1") // SWAGGER
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iEnrrollmentService.update(request, id));
    }

    
}
