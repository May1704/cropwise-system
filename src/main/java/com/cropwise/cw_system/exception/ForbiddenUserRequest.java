package com.cropwise.cw_system.exception;

public class ForbiddenUserRequest extends RuntimeException {
    public ForbiddenUserRequest(String message) {
        super(message);
    }
}
