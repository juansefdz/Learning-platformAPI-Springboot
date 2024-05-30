package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.MessageRequest;
import com.simulacro.aprendizaje.api.dto.response.MessageResponse.MessageResponse;

public interface ImessageService extends CrudService<MessageRequest, MessageResponse, Long> {

}
