package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.MessaggeRequest;
import com.simulacro.aprendizaje.api.dto.response.MessaggeResponse;


public interface ImessaggeService extends CrudService <MessaggeRequest,MessaggeResponse,Long> {
    
}
