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
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ICourseService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import com.simulacro.aprendizaje.utils.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
@Tag(name = "Course Entity Controller", description = "Endpoints to handle courses")
public class CourseController {

    @Autowired
    private final ICourseService iCourseService;

    
    /*----------------------------
     * GET ALL
     * ----------------------------
     */

    @Operation(
        summary = "Displays all Courses",
        description = "Displays the Courses in a list, it is configured to display 10 items per page. "
    ) // SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1") // SWAGGER
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10") // SWAGGER
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iCourseService.getAll(page - 1, size, SortType.NONE));
    }

    /*------------------------------
     * GET BY ID
     * -----------------------------
     */

     @Operation(
        summary = "Displays one course by id",
        description = "Show the course by the ID sent or requested by path,value cannot be less than 1."
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{course_id}")
    public ResponseEntity<CourseResponse> getById(
        @Parameter(description = "Course ID",example = "1") // SWAGGER
        @PathVariable Long course_id) {
        
        CourseResponse course = iCourseService.getById(course_id);
         if (course == null) {
            throw new ResourceNotFoundException("Course not found");
        }
        return ResponseEntity.ok(course);
    }

    /*--------------------
     * CREATE COURSE
     * -------------------
     */

     @Operation(
        summary = "creates a new Course",
        description = "create a new Course by entering the required data"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<CourseResponse> create(
            @Validated @RequestBody CourseRequest request) {
        return ResponseEntity.ok(this.iCourseService.create(request));
    }


     /*----------------------
     * DELETE COURSE
     * ---------------------
     */

     @Operation(
        summary = "Delete Course by ID",
        description = "deletes an Course based on an ID to be sent by Path,value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping(path = "/{course_id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "Course ID",example = "1") // SWAGGER
        @PathVariable Long id) {
        this.iCourseService.delete(id);
        return ResponseEntity.noContent().build();
    }


     /*----------------------
     * UPDATE USER
     * ---------------------
     */

     @Operation(
        summary = "update  Course by ID",
        description = "updates a previously created Course and the ID and the new modified parameters must be sent through the Path, value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(path = "/{course_id}")
    public ResponseEntity<CourseResponse> update(
            @Validated @RequestBody CourseRequest request,
            @Parameter(description = "Course ID",example = "1") // SWAGGER
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iCourseService.update(request, id));
    }

}
