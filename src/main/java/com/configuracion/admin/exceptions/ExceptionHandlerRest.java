package com.configuracion.admin.exceptions;

import com.configuracion.admin.models.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerRest {

    @ExceptionHandler(value =  ProductException.class)
    public @ResponseBody ApiResponse<Void> exception(ProductException exception) {
        return ApiResponse.failure().message(exception.getMessage()).build();
    }

    @ExceptionHandler(value =  ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ApiResponse<Void>  handleConstraintViolationException(ConstraintViolationException e) {
        return ApiResponse.failure().message("Error validation").build();
    }

    @ExceptionHandler( value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ApiResponse<Void> validaArgument(MethodArgumentNotValidException e) {
        return ApiResponse.failure().message("Required Values").build();
    }
}
