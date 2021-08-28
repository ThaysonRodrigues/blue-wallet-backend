package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.Email;

public class VerificarContaRequest {

    @Email(message = "O campo 'email' está inválido")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
