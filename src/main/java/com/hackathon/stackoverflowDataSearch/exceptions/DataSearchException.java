package com.hackathon.stackoverflowDataSearch.exceptions;

public class DataSearchException extends Exception {
    private int errorCode;
    public DataSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
