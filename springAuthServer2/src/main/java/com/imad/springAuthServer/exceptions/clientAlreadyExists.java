package com.imad.springAuthServer.exceptions;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class clientAlreadyExists extends RuntimeException {
     public clientAlreadyExists(String msg){
        super(msg);
    }

}
