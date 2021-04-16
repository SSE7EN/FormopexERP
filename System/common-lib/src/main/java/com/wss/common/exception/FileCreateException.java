package com.wss.common.exception;

public class FileCreateException extends RuntimeException {

    public FileCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCreateException(String message) {
        super(message);
    }
}
