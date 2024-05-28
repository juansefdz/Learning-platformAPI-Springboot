package com.simulacro.aprendizaje.infraestructure.abstract_services;

import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.UserResponse;

@Service
public interface IUserEntityService extends CrudService<UserRequest, UserResponse, Long>  {


    
}
