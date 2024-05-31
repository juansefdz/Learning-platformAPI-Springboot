package com.simulacro.aprendizaje.api.dto.response.CourseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponseIncourse {

    private String lessonTitle;
    private String content;

    
}
