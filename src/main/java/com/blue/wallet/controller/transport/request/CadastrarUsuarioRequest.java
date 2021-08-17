package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class CadastrarUsuarioRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo 'nome' não pode ser nulo ou branco.")
    private String nome;

    @Email(message = "O campo 'email' está inválido")
    private String email;

    @NotBlank(message = "O campo 'celular' não pode ser nulo ou branco.")
    private String celular;

    @NotNull(message = "O campo 'dataNascimento' não pode ser nulo ou branco.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo 'senha' não pode ser nulo ou branco.")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
