package com.bscott.chore.tracker.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id) {
        super("No user found with id " + id);
    }
    public UserNotFoundException(String username, String email) {
        super("No user found with email: " + email + " or username: " + username);
    }
}
