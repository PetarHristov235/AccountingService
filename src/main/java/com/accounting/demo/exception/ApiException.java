package com.accounting.demo.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final String code;
    private int statusCode;

    public ApiException(String code, int statusCode, String message) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
    }
}
