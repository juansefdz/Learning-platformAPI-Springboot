package com.simulacro.aprendizaje.api.dto.request;



import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {

    @Schema(description = "ID of the user being enrolled", example = "123") // SWAGGER
    private Long userId;

    @Schema(description = "ID of the course in which the user is being enrolled", example = "456") // SWAGGER
    private Long courseId;

    @Schema(description = "Date of enrollment", example = "2024-06-01") // SWAGGER
    private Date enrollmentDate;
}

