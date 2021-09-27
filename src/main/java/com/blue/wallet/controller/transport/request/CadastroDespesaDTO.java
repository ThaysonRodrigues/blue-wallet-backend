package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CadastroDespesaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDespesa;

    @NotNull(message = "O campo 'categoriaReceita' não pode ser nulo ou branco.")
    private Integer categoriaDespesa;

    @NotBlank(message = "O campo 'descricao' não pode ser nulo ou branco.")
    private String descricao;

    @NotNull(message = "O campo 'numeroParcelas' não pode ser nulo ou branco.")
    private Integer numeroParcelas;

    @NotNull(message = "O campo 'valor' não pode ser nulo ou branco.")
    private BigDecimal valor;

    @NotNull(message = "O campo 'dataPagamento' não pode ser nulo ou branco.")
    private LocalDate dataPagamento;

    @NotNull(message = "O campo 'flgPagamentoEfetuado' não pode ser nulo ou branco.")
    private boolean flgPagamentoEfetuado;

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Integer getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(Integer categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isFlgPagamentoEfetuado() {
        return flgPagamentoEfetuado;
    }

    public void setFlgPagamentoEfetuado(boolean flgPagamentoEfetuado) {
        this.flgPagamentoEfetuado = flgPagamentoEfetuado;
    }
}
