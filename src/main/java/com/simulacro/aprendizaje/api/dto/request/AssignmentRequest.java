package com.simulacro.aprendizaje.api.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    private String assignmentTitle;
    private String description;
    private Date dueDateAssignment;

}
