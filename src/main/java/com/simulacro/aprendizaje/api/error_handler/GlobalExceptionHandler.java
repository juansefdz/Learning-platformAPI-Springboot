package com.simulacro.aprendizaje.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simulacro.aprendizaje.api.dto.errors.BaseErrorResponse;
import com.simulacro.aprendizaje.api.dto.errors.ListErrorsResponse;
import com.simulacro.aprendizaje.utils.exceptions.ResourceNotFoundException;
import com.simulacro.aprendizaje.utils.exceptions.UnauthorizedException;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ListErrorsResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("error", error.getDefaultMessage());
            errors.add(errorDetails);
        }

        return ListErrorsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseErrorResponse handleBadRequestException(BadRequestException ex) {
        return BaseErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return BaseErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseErrorResponse handleUnauthorizedException(UnauthorizedException ex) {
        return BaseErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .message(ex.getMessage())
                .build();
    }

    // @ExceptionHandler(Exception.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public BaseErrorResponse handleGenericException(Exception ex) {
    //     return BaseErrorResponse.builder()
    //             .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
    //             .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
    //             .message(ex.getLocalizedMessage())
                
    //             .build();
    // }
}
