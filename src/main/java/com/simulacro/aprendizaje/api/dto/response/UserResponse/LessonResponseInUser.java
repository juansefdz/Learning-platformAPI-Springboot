package com.simulacro.aprendizaje.api.dto.response.UserResponse;

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

    private Long idLesson;
    private String lessonTitle;
    private String content;
    private List<AssignmentResponse> assignments;
    
}
