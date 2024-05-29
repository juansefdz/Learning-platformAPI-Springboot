package com.simulacro.aprendizaje.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private String messageContent;
    private Long senderId;
    private Long receiverId;
    private Long courseId;

}
