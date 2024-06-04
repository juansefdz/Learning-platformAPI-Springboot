package com.simulacro.aprendizaje.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {

    @NotNull(message = "The assignment ID is required") // Validation
    private String idAssignment;

    @Schema(description = "Title of the assignment", example = "Math homework") // SWAGGER
    @NotBlank(message = "The assignment title is required") //validation
    @Size(max = 100, message = "The assignment title must have a maximum of 100 characters") //validation
    private String assignmentTitle;

    @Schema(description = "Description of the assignment", example = "Solve exercises 1 to 10") // SWAGGER
    private String description;

    @Schema(description = "Due date of the assignment", example = "2024-06-15") // SWAGGER
    private Date dueDateAssignment;
    @NotNull(message = "The Lesson ID is required") // Validation
    private Long idLesson;

}
