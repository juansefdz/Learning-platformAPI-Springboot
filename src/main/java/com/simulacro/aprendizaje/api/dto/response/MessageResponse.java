package com.simulacro.aprendizaje.api.dto.response;

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

    private Long messageId;
    private String messageContent;
    private Date date;
    private Long senderId;
    private Long receiverId;
    private Long courseId;

}
