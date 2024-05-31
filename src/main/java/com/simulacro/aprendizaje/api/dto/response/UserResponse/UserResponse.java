package com.simulacro.aprendizaje.api.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;
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

    @Schema(description = "ID of the user") // SWAGGER
    private long idUser;

    @Schema(description = "Username of the user") // SWAGGER
    private String userName;

    @Schema(description = "Email of the user") // SWAGGER
    private String email;

    @Schema(description = "Role of the user") // SWAGGER
    private Role role;

    @Schema(description = "List of courses related to the user") // SWAGGER
    private List<CourseResponseInUser> courses;

    @Schema(description = "List of enrollments related to the user") // SWAGGER
    private List<EnrollmentResponseInUser> enrollments;

    @Schema(description = "List of lessons related to the user") // SWAGGER
    private List<LessonResponseInUser> lessons;

    @Schema(description = "List of submissions related to the user") // SWAGGER
    private List<SubmissionResponseInUser> submissions;

    @Schema(description = "List of messages sent by the user") // SWAGGER
    private List<MessageResponse> sentMessages;

    @Schema(description = "List of messages received by the user") // SWAGGER
    private List<MessageResponse> receivedMessages;
}
