package com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {

    @Schema(description = "ID of the enrollment") // SWAGGER
    private Long idEnrollment;

    @Schema(description = "Date of enrollment") // SWAGGER
    private Date enrollmentDate;

    @Schema(description = "User enrolled in the course") // SWAGGER
    private UserResponse user;

    @Schema(description = "Course in which the user is enrolled") // SWAGGER
    private CourseResponse course;
}
