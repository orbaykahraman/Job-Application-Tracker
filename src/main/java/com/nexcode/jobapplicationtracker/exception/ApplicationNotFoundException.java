package com.nexcode.jobapplicationtracker.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String message){
        super(message);
    }
}
