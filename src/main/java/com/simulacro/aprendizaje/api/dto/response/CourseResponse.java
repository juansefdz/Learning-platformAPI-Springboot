package com.simulacro.aprendizaje.api.dto.response;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private Long idCourse;
    private String courseName;
    private String description;
    private Long idInstructor;
    
    private List<EnrollmentResponse> enrollments;
    private List<LessonResponse> lessons;
    private List<MessageResponse> messages;
   

}
