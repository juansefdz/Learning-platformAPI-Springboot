package com.simulacro.aprendizaje.api.dto.response.UserResponse;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseInUser {

    @Schema(description = "ID of the submission") // SWAGGER
    private Long idSubmission;

    @Schema(description = "Content of the submission") // SWAGGER
    private String content;

    @Schema(description = "Grade of the submission") // SWAGGER
    private Double grade;
}

