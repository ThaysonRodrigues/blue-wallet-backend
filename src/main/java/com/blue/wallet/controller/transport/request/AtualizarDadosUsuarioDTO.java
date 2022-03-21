package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtualizarDadosUsuarioDTO {

    @NotBlank(message = "O campo 'nome' não pode ser nulo ou branco.")
    private String nome;

    @NotBlank(message = "O campo 'celular' não pode ser nulo ou branco.")
    private String celular;

    @NotNull(message = "O campo 'dataNascimento' não pode ser nulo ou branco.")
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
