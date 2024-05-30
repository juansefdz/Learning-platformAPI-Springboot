package com.simulacro.aprendizaje.api.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseInUser {

    private Long idCourse;
    private String courseName;
    private String description;
    private Long idInstructor;
    
}
