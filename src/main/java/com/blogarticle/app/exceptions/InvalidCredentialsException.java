package com.blogarticle.app.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message)
    {
        super(message);
    }
}
