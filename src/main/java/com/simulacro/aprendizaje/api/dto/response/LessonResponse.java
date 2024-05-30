package com.simulacro.aprendizaje.api.dto.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    private String lessonTitle;
    private String content;
    

    private List<CourseResponse> courses;
    private List<UserResponse> users;
    private List<AssignmentResponse> assignments;
   

}
