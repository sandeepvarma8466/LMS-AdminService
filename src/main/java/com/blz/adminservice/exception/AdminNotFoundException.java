package com.blz.adminservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AdminNotFoundException extends RuntimeException {
    private int statuscode;
    private String message;

    public AdminNotFoundException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
        this.message = message;
    }
}
