package com.simulacro.aprendizaje.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String lessonTitle;

    @Schema(description = "Content of the lesson", example = "This lesson covers basic Java concepts.") // SWAGGER
    private String content;

    @Schema(description = "Course related to the lesson") // SWAGGER
    private Course course;
}
