package com.example.kbd6.backend.exception.errorclass;

public class UsernameOrPasswordIsIncoException extends  Exception{
    public UsernameOrPasswordIsIncoException() {
        super();
    }

    public UsernameOrPasswordIsIncoException(String message) {
        super(message);
    }

    public UsernameOrPasswordIsIncoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOrPasswordIsIncoException(Throwable cause) {
        super(cause);
    }

    protected UsernameOrPasswordIsIncoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
