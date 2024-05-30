package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.EnrollmentRequest;
import com.simulacro.aprendizaje.api.dto.response.EnrollmentResponse.EnrollmentResponse;

public interface IEnrrollmentService extends CrudService<EnrollmentRequest, EnrollmentResponse, Long> {

}
