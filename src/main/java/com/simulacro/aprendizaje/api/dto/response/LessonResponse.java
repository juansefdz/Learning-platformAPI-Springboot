package com.simulacro.aprendizaje.api.dto.response;

import com.simulacro.aprendizaje.domain.entities.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    private String lessonTitle;
    private String content;
    private Course course;

}
