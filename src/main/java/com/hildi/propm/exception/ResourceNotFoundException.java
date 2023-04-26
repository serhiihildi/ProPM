package com.hildi.propm.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, String id, Long aLong) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}