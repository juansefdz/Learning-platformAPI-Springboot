package com.simulacro.aprendizaje.api.dto.request;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    @Schema(description = "ID of the submission related to the message", example = "1") // SWAGGER
    @NotNull(message = "The id of the assignment is required")
    @Min(value = 1, message = "The id of the assignment must be greater than zero")
    private Long idSubmission;
    private String content;
    private Date SubmissionDate;
    
    private Double grade;

    @Schema(description = "ID of the student related to the message", example = "1") // SWAGGER
    @NotNull(message = "The id of the assignment is required")
    @Min(value = 1, message = "The id of the assignment must be greater than zero")
    private Long idStudent;

}
