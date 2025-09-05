package com.cropwise.cw_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenUserRequest.class)
    public ResponseEntity<ErrorResponse> handleForbiddenUserRequest(ForbiddenUserRequest ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ValueOutOfRange.class)
    public ResponseEntity<ErrorResponse> ValueOutOfRange(ValueOutOfRange ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CropNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCropNotFoundException(CropNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
