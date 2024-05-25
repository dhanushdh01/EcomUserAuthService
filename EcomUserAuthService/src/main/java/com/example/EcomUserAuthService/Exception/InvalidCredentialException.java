package com.example.EcomUserAuthService.Exception;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException() {

    }
    public InvalidCredentialException(String message) {
        super(message);
    }
}
