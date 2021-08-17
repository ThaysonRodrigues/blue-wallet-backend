package com.blue.wallet.exceptions;

public class ValidationBusinessException extends BusissException {

    public ValidationBusinessException(String message) {
        super.setMessage(message);
    }
}
