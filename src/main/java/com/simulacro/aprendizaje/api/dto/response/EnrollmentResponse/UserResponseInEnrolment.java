package com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseInEnrolment {
    private Long idUser;
    private String userName;
    private String email;
    
}
