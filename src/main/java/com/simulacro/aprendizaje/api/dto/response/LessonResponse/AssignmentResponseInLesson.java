package com.simulacro.aprendizaje.api.dto.response.LessonResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponseInLesson {

    private Long idAssigment;
    private String assignmentTitle;
    private String description;
    
}
