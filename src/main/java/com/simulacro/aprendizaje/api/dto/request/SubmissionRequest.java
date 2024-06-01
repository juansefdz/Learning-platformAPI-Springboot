package com.simulacro.aprendizaje.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {

    @NotNull(message = "The id of the assignment is required")
    @Min(value = 1, message = "The id of the assignment must be greater than zero")
    private Long idSubmission;
    private String content;
    private Double grade;
    @NotNull(message = "The id of the assignment is required")
    @Min(value = 1, message = "The id of the assignment must be greater than zero")
    private Long idStudent;

}
