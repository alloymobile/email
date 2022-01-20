package com.alloymobiletech.email.exception;

import com.alloymobiletech.email.model.ResponseDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleRunTimeException(NotFoundException e, HandlerMethod handlerMethod, HttpServletRequest request){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTimestamp(ZonedDateTime.now());
        responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
        responseDTO.setMessage(e.getMessage());
        responseDTO.setPath(request.getRequestURL().toString());
        responseDTO.setServiceName(handlerMethod.getMethod().getDeclaringClass().getName());
        responseDTO.setControllerName(handlerMethod.getMethod().getName());
        return new ResponseEntity<>(responseDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleRunTimeException(InternalServerException e, HandlerMethod handlerMethod, HttpServletRequest request){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTimestamp(ZonedDateTime.now());
        responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseDTO.setMessage(e.getMessage());
        responseDTO.setPath(request.getRequestURL().toString());
        responseDTO.setServiceName(handlerMethod.getMethod().getDeclaringClass().getName());
        responseDTO.setControllerName(handlerMethod.getMethod().getName());
        return new ResponseEntity<>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}