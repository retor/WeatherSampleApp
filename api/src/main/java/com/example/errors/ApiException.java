package com.example.errors;

/**
 * Created by retor on 16.03.2016.
 */
public class ApiException extends Exception {
    private int statusCode;

    public ApiException() {
        super("Api error");
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ApiException setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}