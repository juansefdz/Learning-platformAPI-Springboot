package com.simulacro.aprendizaje.api.dto.response.LessonResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;

import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    @Schema(description = "Title of the lesson") // SWAGGER
    private String lessonTitle;

    @Schema(description = "Content of the lesson") // SWAGGER
    private String content;

    @Schema(description = "List of courses related to the lesson") // SWAGGER
    private CourseResponseInLesson courses;

    @Schema(description = "List of users related to the lesson") // SWAGGER
    private List<UserResponse> users;

    @Schema(description = "List of assignments related to the lesson") // SWAGGER
    private List<AssignmentResponseInLesson> assignments;
}
