package com.bscott.chore.tracker.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
