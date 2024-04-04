package com.homs4j.expendlr.app.exception;

public class EntityNotUpdatedException extends RuntimeException {

    public EntityNotUpdatedException() {
    }

    public EntityNotUpdatedException(String message) {
        super(message);
    }
}
