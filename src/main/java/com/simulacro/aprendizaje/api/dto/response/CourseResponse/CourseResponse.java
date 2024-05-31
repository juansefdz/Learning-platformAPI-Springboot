package com.simulacro.aprendizaje.api.dto.response.CourseResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse.EnrollmentResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    @Schema(description = "ID of the course") // SWAGGER
    private Long idCourse;

    @Schema(description = "Name of the course") // SWAGGER
    private String courseName;

    @Schema(description = "Description of the course") // SWAGGER
    private String description;

    @Schema(description = "ID of the instructor") // SWAGGER
    private Long idInstructor;

    @Schema(description = "List of enrollments related to the course") // SWAGGER
    private List<EnrollmentResponseInCourse> enrollments;

    @Schema(description = "List of lessons related to the course") // SWAGGER
    private List<LessonResponseIncourse> lessons;

    @Schema(description = "List of messages related to the course") // SWAGGER
    private List<MessageResponse> messages;
}
