package com.simulacro.aprendizaje.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import com.simulacro.aprendizaje.utils.enums.Role; 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Schema(description = "Username of the user", example = "john_doe") // SWAGGER
    @NotBlank(message = "The username is required") // validation
    @Size(max = 50, message = "The userName must have a maximum of 50 characters") // validation
    private String userName;

    @Schema(description = "Password of the user") // SWAGGER
    @NotBlank(message = "The password is required") // validation
    private String password;

    @Schema(description = "Email of the user", example = "john@example.com") // SWAGGER
    @NotBlank(message = "The email is required") // validation
    @Size(max = 100, message = "The email must have a maximum of 100 characters") // validation
    private String email;

    @Schema(description = "Full name of the user", example = "John Doe") // SWAGGER
    @NotBlank(message = "The fullName is required") // validation
    @Size(max = 100, message = "The fullName must have a maximum of 100 characters") // validation
    private String fullName;

    @Schema(description = "Role of the user") // SWAGGER
    @NotNull(message = "The role is required") // validation
    private Role role;
}

