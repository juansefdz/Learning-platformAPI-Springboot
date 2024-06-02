package com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseInSubmission {

    private Long idUser;
    private String userName;
    private String fullName;

    
}
