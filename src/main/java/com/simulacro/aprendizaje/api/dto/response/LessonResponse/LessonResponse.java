package com.simulacro.aprendizaje.api.dto.response.LessonResponse;

import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse.AssignmentResponse;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.UserResponse.UserResponse;

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
