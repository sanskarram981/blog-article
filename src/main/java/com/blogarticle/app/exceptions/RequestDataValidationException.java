package com.blogarticle.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDataValidationException extends RuntimeException{
    private String resource;
    public RequestDataValidationException(String resource, String message)
    {
        super(String.format("%s: %s",resource,message));
        this.resource = resource;
    }
}
