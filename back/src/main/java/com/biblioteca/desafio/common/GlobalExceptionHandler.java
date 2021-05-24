package com.biblioteca.desafio.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> error(Exception ex, HttpStatus status) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorCode(ErrorResponse.getErrorCode(ex).orElse(0L))
                        .message(ErrorResponse.getMessage(ex))
                        .status(status.value())
                        .timestamp(ErrorResponse.getDate())
                        .build()
        , status);
    }

}
