package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.NotNull;

public class AtualizarSenhaDTO {

    @NotNull(message = "O campo 'email' não pode ser nulo ou branco.")
    private String email;

    @NotNull(message = "O campo 'senha' não pode ser nulo ou branco.")
    private String senha;

    @NotNull(message = "O campo 'codigo' não pode ser nulo ou branco.")
    private Integer codigo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
