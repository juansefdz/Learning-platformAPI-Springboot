package com.simulacro.aprendizaje.api.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {

    private Long idEnrollment;
    private Date enrollmentDate;
    private UserResponse user;
    private CourseResponse course;


}
