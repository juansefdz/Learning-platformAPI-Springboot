package com.simulacro.aprendizaje.api.dto.response.LessonResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseInLesson {

    private Long idCourse;
    private String courseName;
    private String description;
    private Long instructorId;
    private String instructorName;
    
}
