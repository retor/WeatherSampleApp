package com.example.errors;

/**
 * Created by retor on 15.03.2016.
 */
public class ServerError {
    private int statusCode;
    private String message;

    public ServerError() {
    }

    public ServerError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
