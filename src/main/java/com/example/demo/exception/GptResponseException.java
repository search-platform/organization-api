package com.example.demo.exception;

public class GptResponseException extends RuntimeException {

    public GptResponseException() {
    }

    public GptResponseException(String message) {
        super(message);
    }

    public GptResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GptResponseException(Throwable cause) {
        super(cause);
    }

    public GptResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
