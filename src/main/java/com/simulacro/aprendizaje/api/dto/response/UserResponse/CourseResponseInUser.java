package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseInUser {

    @Schema(description = "ID of the course") // SWAGGER
    private Long idCourse;

    @Schema(description = "Name of the course") // SWAGGER
    private String courseName;

    @Schema(description = "Description of the course") // SWAGGER
    private String description;

    @Schema(description = "ID of the instructor") // SWAGGER
    private Long idInstructor;
}
