package com.simulacro.aprendizaje.api.dto.response.MessageResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    @Schema(description = "ID of the message") // SWAGGER
    private Long messageId;

    @Schema(description = "Content of the message") // SWAGGER
    private String messageContent;

    @Schema(description = "Date of the message") // SWAGGER
    private Date date;

    @Schema(description = "ID of the sender of the message") // SWAGGER
    private Long senderId;

    @Schema(description = "ID of the receiver of the message") // SWAGGER
    private Long receiverId;

    @Schema(description = "ID of the course related to the message") // SWAGGER
    private Long courseId;
}
