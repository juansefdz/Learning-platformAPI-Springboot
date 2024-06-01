package com.simulacro.aprendizaje.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    @Schema(description = "Name of the course", example = "Introduction to Programming") // SWAGGER
    @NotBlank(message = "The course name is required") //validation
    @Size(max = 100, message = "The course name must have a maximum of 100 characters") //validation
    private String lessonTitle;
    private String courseName;

    @Schema(description = "Description of the course", example = "A comprehensive introduction to programming concepts") // SWAGGER
    private String description;

    @Schema(description = "ID of the instructor teaching the course", example = "123") // SWAGGER
    private Long instructorId;
}
