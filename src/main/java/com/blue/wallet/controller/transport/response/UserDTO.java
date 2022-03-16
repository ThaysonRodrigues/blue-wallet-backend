package com.blue.wallet.controller.transport.response;

public class UserDTO {

    private String nome;

    public UserDTO(String nomeUsuario) {
        this.nome = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeUsuario) {
        this.nome = nomeUsuario;
    }
}
