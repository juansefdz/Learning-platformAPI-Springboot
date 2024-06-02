package com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseInEnrolment {

    
    private Long courseId;
    private String courseName;
    private String description;
    private Long instructorId;
    
}
