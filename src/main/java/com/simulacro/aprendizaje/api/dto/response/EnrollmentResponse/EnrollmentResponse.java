package com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;

import java.util.Date;

import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;

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
