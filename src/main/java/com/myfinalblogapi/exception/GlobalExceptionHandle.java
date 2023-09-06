package com.myfinalblogapi.exception;

import com.myfinalblogapi.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException (ResourceNotFounException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));


ResponseEntity response = new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

return response;
    }
@ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorDetails> HandleException(Exception exception,WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        ResponseEntity response = new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);

        return response;

    }
}