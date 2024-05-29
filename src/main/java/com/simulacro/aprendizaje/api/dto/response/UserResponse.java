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
    private String password;
    private String email;
    private String fullName;
    private Role role;


    private CourseResponse course;
    private EnrollmentResponse enrollments;
    private MessageResponse messages;
    private SubmissionResponse submission;


   
}
