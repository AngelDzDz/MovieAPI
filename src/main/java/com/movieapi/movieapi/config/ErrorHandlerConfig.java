package com.movieapi.movieapi.config;

import com.movieapi.movieapi.exceptions.GeneralServiceException;
import com.movieapi.movieapi.exceptions.NoAuthorizationException;
import com.movieapi.movieapi.exceptions.NoDataFoundException;
import com.movieapi.movieapi.exceptions.ValidateServiceException;
import com.movieapi.movieapi.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request) {
        log.error(e.getMessage(),e);
        WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error",null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAuthorizationException.class)
    public ResponseEntity<?> noAuthorizationException(NoAuthorizationException e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage() ,null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error",null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
