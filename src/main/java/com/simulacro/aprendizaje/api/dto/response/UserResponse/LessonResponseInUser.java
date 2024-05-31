package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponseInUser {

    @Schema(description = "ID of the lesson") // SWAGGER
    private Long idLesson;

    @Schema(description = "Title of the lesson") // SWAGGER
    private String lessonTitle;

    @Schema(description = "Content of the lesson") // SWAGGER
    private String content;

    @Schema(description = "List of assignments related to the lesson") // SWAGGER
    private List<AssignmentResponse> assignments;
}
