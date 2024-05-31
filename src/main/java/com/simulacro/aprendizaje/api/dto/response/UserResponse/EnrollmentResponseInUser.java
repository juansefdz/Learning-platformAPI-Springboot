package com.simulacro.aprendizaje.api.dto.response.UserResponse;

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
public class EnrollmentResponseInUser {

    @Schema(description = "ID of the enrollment") // SWAGGER
    private Long idEnrollment;

    @Schema(description = "Date of enrollment") // SWAGGER
    private Date enrollmentDate;
}
