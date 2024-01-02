package com.blogarticle.app.exceptions;

import com.blogarticle.app.payloads.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponseDto> handleRuntimeException(RuntimeException runtimeException)
    {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Something went wrong");
        apiResponseDto.setSuccess(false);
        apiResponseDto.setData(Collections.singletonList(runtimeException.getMessage()));

        return new ResponseEntity<>(apiResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponseDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException)
    {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage(resourceNotFoundException.getMessage());
        apiResponseDto.setSuccess(false);
        apiResponseDto.setData(null);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceAlreadyFoundException.class})
    //@ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiResponseDto> handleResourceAlreadyFoundException(ResourceAlreadyFoundException resourceAlreadyFoundException)
    {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage(resourceAlreadyFoundException.getMessage());
        apiResponseDto.setSuccess(false);
        apiResponseDto.setData(null);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RequestDataValidationException.class})
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseDto> handleRequestDataValidationException(RequestDataValidationException requestDataValidationException)
    {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage(requestDataValidationException.getMessage());
        apiResponseDto.setSuccess(false);
        apiResponseDto.setData(null);

        return new ResponseEntity<>(apiResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseDto> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException)
    {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage(invalidCredentialsException.getMessage());
        apiResponseDto.setSuccess(false);
        apiResponseDto.setData(null);

        return new ResponseEntity<>(apiResponseDto,HttpStatus.BAD_REQUEST);
    }

}
