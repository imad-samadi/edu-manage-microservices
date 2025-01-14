package com.micro.ModuleService.exceptions;

public class ModuleExists extends RuntimeException {
    public ModuleExists(String message) {
     super(message);
    }
}

