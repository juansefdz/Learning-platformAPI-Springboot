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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Tag(name = "User Entity Controller") // SWAGGER
public class UserEntityController {

    @Autowired
    private final IUserEntityService objIUserEntityService;


    /*----------------------------
     * GET ALL
     * ----------------------------
     */

    @Operation(
        summary = "Displays all Users",
        description = "Displays the users in a list, it is configured to display 10 items per page. "
    ) // SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved User List"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the list of users. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })// SWAGGER
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @Parameter(description = "Page number (default: 1)", example = "1") // SWAGGER
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10") // SWAGGER
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.objIUserEntityService.getAll(page - 1, size, SortType.NONE));
    }

    /*------------------------------
     * GET BY ID
     * -----------------------------
     */

     @Operation(
        summary = "Displays one user by id",
        description = "Shows the user by the ID sent or requested by path,value cannot be less than 1."
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved User"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })// SWAGGER
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(
            @Parameter(description = "User ID",example = "1") // SWAGGER
            @PathVariable Long id) {

        return ResponseEntity.ok(this.objIUserEntityService.getById(id));
    }

    /*--------------------
     * CREATE USER
     * -------------------
     */

     @Operation(
        summary = "creates a new user",
        description = "create a new user by entering the required data"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created User"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to create a user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })//SWAGGER
    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> create(
            @Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.objIUserEntityService.create(request));
    }

    /*----------------------
     * DELETE USER
     * ---------------------
     */

     @Operation(
        summary = "Delete user by ID",
        description = "deletes an user based on an ID to be sent by Path,value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted User"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to delete the user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    }) //SWAGGER
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "User ID",example = "1") // SWAGGER
            @PathVariable Long id) {
        this.objIUserEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*----------------------
     * UPDATE USER
     * ---------------------
     */

     @Operation(
        summary = "update  user by ID",
        description = "updates a previously created user and the ID and the new modified parameters must be sent through the Path, value cannot be less than 1"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated User"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to update the user. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    }) //SWAGGER
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserResponse> update(
            @Validated @RequestBody UserRequest request,
            @Parameter(description = "User ID",example = "1") // SWAGGER
            @PathVariable Long id) {
        return ResponseEntity.ok(this.objIUserEntityService.update(request, id));
    }
}
