package com.example.kbd6.backend.exception.handler;

import com.example.kbd6.backend.exception.errorMessage.ErrorMessage;
import com.example.kbd6.backend.exception.errorclass.DuplicateVendorNameException;
import com.example.kbd6.backend.exception.errorclass.UserEmailAlreadyExistException;
import com.example.kbd6.backend.exception.errorclass.UsernameOrPasswordIsIncoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateVendorNameException.class)
    public ResponseEntity<ErrorMessage> duplicateVendorNameException(DuplicateVendorNameException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(UserEmailAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> userEmailAlreadyExistException(UserEmailAlreadyExistException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(UsernameOrPasswordIsIncoException.class)
    public ResponseEntity<ErrorMessage> usernameOrPasswordIsIncoException(UsernameOrPasswordIsIncoException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
