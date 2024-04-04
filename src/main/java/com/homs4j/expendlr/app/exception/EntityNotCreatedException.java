package com.homs4j.expendlr.app.exception;

public class EntityNotCreatedException extends RuntimeException {

    public EntityNotCreatedException() {
        super("Data was not created");
    }

    public EntityNotCreatedException(String message) {
        super(message);
    }
}
