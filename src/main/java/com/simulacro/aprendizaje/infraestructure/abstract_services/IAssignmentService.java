package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.AssignmentRequest;
import com.simulacro.aprendizaje.api.dto.response.AssignmentResponse;

public interface IAssignmentService extends CrudService<AssignmentRequest, AssignmentResponse, Long> {

}
