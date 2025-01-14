package com.micro.studentService.exceptions;

public class notFoundException extends RuntimeException {
    public notFoundException(String msg){
        super(msg);
    }
}
