package com.lightspeed.incrementor.configuration;

import com.lightspeed.incrementor.exception.CounterException;
import com.lightspeed.incrementor.model.ExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Custom handler
 */
@RestControllerAdvice
public class CounterExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CounterException.class)
    private ResponseEntity<ExceptionModel> handleCounterException(CounterException ex){
        ExceptionModel error = new ExceptionModel(HttpStatus.BAD_REQUEST, "Counter Exception", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
