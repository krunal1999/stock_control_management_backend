package com.example.kbd6.backend.exception.errorclass;

public class DuplicateVendorNameException extends Exception{
    public DuplicateVendorNameException(String message) {
        super(message);
    }

    public DuplicateVendorNameException() {
        super();
    }

    public DuplicateVendorNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateVendorNameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateVendorNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
