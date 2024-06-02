package com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
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

    @Schema(description = "ID of the submission") // SWAGGER
    private Long idSubmission;

    @Schema(description = "Content of the submission") // SWAGGER
    private String content;

    @Schema(description = "Grade of the submission") // SWAGGER
    private Double grade;
    
    @Schema(description = "Grade of the submission") // SWAGGER
    private Date SubmissionDate;

    @Schema(description = "List of users related to the submission") // SWAGGER
    private List<UserResponseInSubmission> users;

    @Schema(description = "List of assignments related to the submission") // SWAGGER
    private List<AssignmentResponseInSubmission> assignments;
}
