package com.blue.wallet.exceptions;

public class BusissException extends Exception {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
