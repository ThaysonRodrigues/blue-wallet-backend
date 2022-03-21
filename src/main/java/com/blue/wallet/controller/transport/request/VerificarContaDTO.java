package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.Email;

public class VerificarContaDTO {

    @Email(message = "O campo 'email' está inválido")
    private String email;

    private String nome;

    private String idGoogle;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }
}
