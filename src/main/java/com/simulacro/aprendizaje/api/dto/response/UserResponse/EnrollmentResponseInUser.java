package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseInUser {
    private Long idEnrollment;
    private Date enrollmentDate;
    
}
