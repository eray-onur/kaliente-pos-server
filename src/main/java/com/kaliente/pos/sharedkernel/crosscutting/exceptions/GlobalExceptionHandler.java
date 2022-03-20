package com.kaliente.pos.sharedkernel.crosscutting.exceptions;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kaliente.pos.application.models.base.BaseResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler 
  extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> globalExceptionHandler(Exception ex, WebRequest request) {
      return new ResponseEntity<BaseResponse<?>>(new BaseResponse<String>(null, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}