package com.blue.wallet.controller.transport.response;

public class WebServiceErrorResponse {

    private String message;

    public WebServiceErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
