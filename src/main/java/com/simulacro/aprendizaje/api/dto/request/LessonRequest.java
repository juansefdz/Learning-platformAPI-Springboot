package com.simulacro.aprendizaje.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.simulacro.aprendizaje.domain.entities.Course; // Asegúrate de importar la clase Course correcta

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {

    @Schema(description = "Title of the lesson", example = "Introduction to Java") // SWAGGER
    @NotBlank(message = "The lesson title is required") //validation
    @Size(max = 100, message = "The lesson title must have a maximum of 100 characters") //validation
    private String lessonTitle;

    @Schema(description = "Content of the lesson", example = "This lesson covers basic Java concepts.") // SWAGGER
    private String content;

    @Schema(description = "Course related to the lesson") // SWAGGER
    private Course course;
}
