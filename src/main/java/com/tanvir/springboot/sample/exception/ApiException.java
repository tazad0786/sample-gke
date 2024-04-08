package com.tanvir.springboot.sample.exception;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }
}
