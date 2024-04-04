package com.homs4j.expendlr.app.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
        super("Data not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
