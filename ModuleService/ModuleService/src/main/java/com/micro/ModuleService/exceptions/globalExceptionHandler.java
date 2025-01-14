package com.micro.ModuleService.exceptions;


import com.micro.ModuleService.DTO.errorResponse ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<errorResponse> handleNameExists(ModuleExists studentExists, WebRequest webRequest){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        errorResponse.builder()
                                .apiPath(webRequest.getDescription(false))
                                .errorCode(HttpStatus.BAD_REQUEST)
                                .errorMsg(studentExists.getMessage())
                                .errorTime(LocalDateTime.now())
                                .build()
                ) ;
    }

    @ExceptionHandler
    public ResponseEntity<errorResponse> UnknownException(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        errorResponse.builder()
                                .apiPath(null)
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                                .errorMsg(ex.getMessage())
                                .errorTime(LocalDateTime.now())
                                .build()
                ) ;
    }
    @ExceptionHandler
    public ResponseEntity<errorResponse> handleNameExists(notFoundException notFoundException, WebRequest webRequest){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        errorResponse.builder()
                                .apiPath(webRequest.getDescription(false))
                                .errorCode(HttpStatus.BAD_REQUEST)
                                .errorMsg(notFoundException.getMessage())
                                .errorTime(LocalDateTime.now())
                                .build()
                ) ;
    }

}
