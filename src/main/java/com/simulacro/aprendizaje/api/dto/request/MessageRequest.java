package com.simulacro.aprendizaje.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @Schema(description = "ID of the message", example = "1") // SWAGGER
    private Long messageId;

    @Schema(description = "Content of the message", example = "Hello, how are you?") // SWAGGER
    private String messageContent;

    @Schema(description = "ID of the sender of the message", example = "123") // SWAGGER
    private Long senderId;

    @Schema(description = "ID of the receiver of the message", example = "456") // SWAGGER
    private Long receiverId;

    @Schema(description = "ID of the course related to the message", example = "789") // SWAGGER
    private Long courseId;
}

