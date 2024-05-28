package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.LessonRequest;
import com.simulacro.aprendizaje.api.dto.response.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse, Long> {

}
