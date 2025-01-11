package com.imad.springAuthServer.exceptions;

public class notFoundException extends RuntimeException {
    public notFoundException(String msg){
        super(msg);
    }
}
