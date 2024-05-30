package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import java.util.List;

import com.simulacro.aprendizaje.api.dto.response.CourseResponse.CourseResponse;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse.LessonResponse;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse.SubmissionResponse;
import com.simulacro.aprendizaje.domain.entities.Enrollment;
import com.simulacro.aprendizaje.utils.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long idUser;
    private String userName;
    private String email;
    private Role role;

    private List<CourseResponseInUser> courses;
    private List<EnrollmentResponseInUser> enrollments;
    private List<LessonResponseInUser> lessons;
    private List<SubmissionResponseInUser> submissions;
    private List<MessageResponse> sentMessages;
    private List<MessageResponse> receivedMessages;


   
}
