package com.bscott.chore.tracker.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(Integer id) {
        super("User with id " + id + " already exists");
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
