package com.simulacro.aprendizaje.api.dto.response.LessonResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseInLesson {

    private Long idUser; 
    private String userName;
    private String email;
    private String fullName;
    
}
