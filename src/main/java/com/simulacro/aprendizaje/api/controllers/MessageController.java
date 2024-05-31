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


import com.simulacro.aprendizaje.api.dto.request.MessageRequest;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessageService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/messages")
@AllArgsConstructor
@Tag(name = "Message Controller") // SWAGGER
public class MessageController {

    private final ImessageService objImessageService;

    /*----------------
     * GET ALL
     * ---------------
     */

    @Operation(
        summary = "Displays all Messages",
        description = "Displays the messages in a list, it is configured to display 10 items per page."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Message List"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the list of messages. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAll(
            @Parameter(description = "Page number (default: 0)", example = "0")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(objImessageService.getAll(page - 1, size, SortType.NONE));
    }

    /*------------------
     * GET BY ID
     * -----------------
     */

    @Operation(
        summary = "Displays one message by id",
        description = "Shows the message by the ID sent or requested by path, value cannot be less than 1."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Message"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to view the message. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(objImessageService.getById(id));
    }

    /*----------------------
     * CREATE
     * ---------------------
     */
    @Operation(
        summary = "Creates a new message",
        description = "Creates a new message by entering the required data"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created Message"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to create a message. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<MessageResponse> create(
            @Validated @RequestBody MessageRequest request) {
        return ResponseEntity.ok(objImessageService.create(request));
    }

    /*-------------------
     * DELETE
     * ------------------
     */

    @Operation(
        summary = "Delete message by ID",
        description = "Deletes a message based on an ID sent by path, value cannot be less than 1"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted Message"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Not authorized to delete the message. Invalid token"),
        @ApiResponse(responseCode = "403", description = "Forbidden access"),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error. Please contact support")
    })

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objImessageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
