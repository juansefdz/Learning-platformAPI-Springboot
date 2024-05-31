package com.simulacro.aprendizaje.api.dto.response.CourseResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseInCourse {

    private Long idEnrollment;
    private Date enrollmentDate;
    
}
