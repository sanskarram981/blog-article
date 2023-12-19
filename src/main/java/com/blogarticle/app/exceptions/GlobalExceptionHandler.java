package com.blogarticle.app.exceptions;

import com.blogarticle.app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException runtimeException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Something went wrong");
        apiResponse.setSuccess(false);
        apiResponse.setData(Collections.singletonList(runtimeException.getMessage()));

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(resourceNotFoundException.getMessage());
        apiResponse.setSuccess(false);
        apiResponse.setData(null);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceAlreadyFoundException.class})
    public ResponseEntity<ApiResponse> handleResourceAlreadyFoundException(ResourceAlreadyFoundException resourceAlreadyFoundException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(resourceAlreadyFoundException.getMessage());
        apiResponse.setSuccess(false);
        apiResponse.setData(null);

        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RequestDataValidationException.class})
    public ResponseEntity<ApiResponse> handleRequestDataValidationException(RequestDataValidationException requestDataValidationException)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(requestDataValidationException.getMessage());
        apiResponse.setSuccess(false);
        apiResponse.setData(null);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}
