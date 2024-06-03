package com.simulacro.aprendizaje.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.simulacro.aprendizaje.api.dto.request.MessageRequest;

import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.ImessageService;
import com.simulacro.aprendizaje.utils.enums.SortType;
import com.simulacro.aprendizaje.utils.exceptions.ResourceNotFoundException;

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

    private final ImessageService imessageService;

    /*----------------
     * GET ALL
     * ---------------
     */

    @Operation(
        summary = "Displays all Messages",
        description = "Displays the messages in a list, it is configured to display 10 items per page."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAll(
            @Parameter(description = "Page number (default: 0)", example = "0")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number of items per page (default: 10)", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(imessageService.getAll(page - 1, size, SortType.NONE));
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
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{message_id}")
    public ResponseEntity<MessageResponse> getById(
        @Parameter(description = "Message ID", example = "1")
        @PathVariable Long message_id) {

         MessageResponse message = imessageService.getById(message_id);
        if (message == null) {
            throw new ResourceNotFoundException("Assignment not found");
        }
        return ResponseEntity.ok(message);
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
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<MessageResponse> create(
            @Validated @RequestBody MessageRequest request) {
        return ResponseEntity.ok(imessageService.create(request));
    }

    
}
