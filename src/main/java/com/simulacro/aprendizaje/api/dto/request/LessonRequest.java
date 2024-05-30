package com.simulacro.aprendizaje.api.dto.request;

import com.simulacro.aprendizaje.domain.entities.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {

    private String lessonTitle;
    private String content;
    private Course course;

}
