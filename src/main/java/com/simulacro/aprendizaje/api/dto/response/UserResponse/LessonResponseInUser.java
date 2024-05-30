package com.simulacro.aprendizaje.api.dto.response.UserResponse;

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
    
}
