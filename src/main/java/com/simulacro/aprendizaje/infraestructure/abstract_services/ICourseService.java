package com.simulacro.aprendizaje.infraestructure.abstract_services;

import com.simulacro.aprendizaje.api.dto.request.CourseRequest;
import com.simulacro.aprendizaje.api.dto.response.CourseResponse;

public interface ICourseService extends CrudService <CourseRequest,CourseResponse,Long>{
    
}
