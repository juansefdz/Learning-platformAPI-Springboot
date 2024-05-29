package com.simulacro.aprendizaje.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {
    private Long idSubmission;
    private String content;
    private Double grade;
    private UserResponse user;
    private AssignmentResponse assignment;

}
