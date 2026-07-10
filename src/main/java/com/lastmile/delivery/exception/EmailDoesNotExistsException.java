package com.lastmile.delivery.exception;

public class EmailDoesNotExistsException extends RuntimeException{
    public EmailDoesNotExistsException(String message) {
        super(message);
    }   
}
