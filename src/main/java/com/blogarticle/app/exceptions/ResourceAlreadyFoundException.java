package com.blogarticle.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceAlreadyFoundException extends RuntimeException{
    private String resource;
    private String fieldName;
    private String fieldValue;
    public ResourceAlreadyFoundException(String resource,String fieldName,String fieldValue)
    {
        super(String.format("%s already exist with %s : %s",resource,fieldName,fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
