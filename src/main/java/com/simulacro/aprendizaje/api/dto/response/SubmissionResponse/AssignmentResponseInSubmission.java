package com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponseInSubmission {

    private Long idAssignment;
    private String assignmentTitle;
    private String description;
    private Date dueDateAssignment;
    private Long idLesson;
    
}
