package com.example.config;

import lombok.Data;

@Data
public class DBException extends Exception {
    private String message;

    public DBException(String message) {
        super(message);
    }
}
