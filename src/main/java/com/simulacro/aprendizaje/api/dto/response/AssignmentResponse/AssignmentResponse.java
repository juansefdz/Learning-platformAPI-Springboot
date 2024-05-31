package com.simulacro.aprendizaje.api.dto.response.AssignmentResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {

    @Schema(description = "ID of the assignment") // SWAGGER
    private Long idAssignment;

    @Schema(description = "Title of the assignment") // SWAGGER
    private String assignmentTitle;

    @Schema(description = "Description of the assignment") // SWAGGER
    private String description;

    @Schema(description = "Due date of the assignment") // SWAGGER
    private Date dueDateAssignment;
}
