package com.bscott.chore.tracker.exception;

public class NullEntityException extends RuntimeException{

    public NullEntityException(String entityName) {
        super("Null " + entityName + " cannot be saved or updated");
    }
}
