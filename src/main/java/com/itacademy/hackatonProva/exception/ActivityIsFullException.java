package com.itacademy.hackatonProva.exception;

public class ActivityIsFullException  extends RuntimeException {
    public ActivityIsFullException(String message) {
        super(message);
    }
}