package com.bscott.chore.tracker.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id) {
        super("No user found with id " + id);
    }
}
