package com.simulacro.aprendizaje.infraestructure.abstract_services;

import org.springframework.stereotype.Service;

import com.simulacro.aprendizaje.api.dto.request.SubmissionRequest;
import com.simulacro.aprendizaje.api.dto.response.SubmissionResponse;

@Service
public interface ISubmissionService extends CrudService<SubmissionRequest, SubmissionResponse, Long> {

}
