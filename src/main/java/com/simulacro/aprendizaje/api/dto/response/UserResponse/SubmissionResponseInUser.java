package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseInUser {
    private Long idSubmission;
    private String content;
    private Double grade; 
}
