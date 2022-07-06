package com.bobocode.fp.exception;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
