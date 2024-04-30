package com.example.kbd6.backend.exception.errorclass;

public class UserEmailAlreadyExistException extends Exception{
    public UserEmailAlreadyExistException() {
        super();
    }

    public UserEmailAlreadyExistException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected UserEmailAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
