package com.simulacro.aprendizaje.api.dto.response.AssignmentResponse;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonsResponseInAssignment {

   private Long lessonId;
    private String lessonTitle;
    private String content;
    private Long courseId;
    
    
}
