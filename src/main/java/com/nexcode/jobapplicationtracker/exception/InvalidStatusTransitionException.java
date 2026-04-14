package com.nexcode.jobapplicationtracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidStatusTransitionException extends RuntimeException{

    public  InvalidStatusTransitionException (String message){
        super(message);
    }

}
