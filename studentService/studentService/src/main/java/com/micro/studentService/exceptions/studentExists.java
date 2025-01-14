package com.micro.studentService.exceptions;

public class studentExists extends RuntimeException {
    public studentExists(String message) {
     super(message);
    }
}

