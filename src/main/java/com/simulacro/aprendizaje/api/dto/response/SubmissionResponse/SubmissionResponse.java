package com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;

import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;

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
    private List<UserResponse> users;
    private List<AssignmentResponse> assignments;
}
