package com.simulacro.aprendizaje.api.dto.response;

import java.util.List;

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

    private List<CourseResponse> courses;
    private List<EnrollmentResponse> enrollments;
    private List<MessageResponse> sentMessages;
    private List<MessageResponse> receivedMessages;
    private List<LessonResponse> lessons;
    private List<SubmissionResponse> submissions;


   
}
