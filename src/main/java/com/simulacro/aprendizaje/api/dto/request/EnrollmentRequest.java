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
public class EnrollmentRequest {

    private Long userId;
    private Long courseId;
    private Date enrollmentDate;

}
