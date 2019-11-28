package com.bscott.chore.tracker.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id) {
        super("No user found with id " + id);
    }
    public UserNotFoundException(String username) {
        super("No user found with username: " + username);
    }
}
