package com.simulacro.aprendizaje.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
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

    
    private String idAssignment;
    @Schema(description = "Title of the assignment", example = "Math homework") // SWAGGER
    private String assignmentTitle;

    @Schema(description = "Description of the assignment", example = "Solve exercises 1 to 10") // SWAGGER
    private String description;

    @Schema(description = "Due date of the assignment", example = "2024-06-15") // SWAGGER
    private Date dueDateAssignment;
}
