package com.simulacro.aprendizaje.api.dto.response;

import com.simulacro.aprendizaje.utils.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {
    private long idUser;
    private String userName;
    private String email;
    private Role role;
    
}
