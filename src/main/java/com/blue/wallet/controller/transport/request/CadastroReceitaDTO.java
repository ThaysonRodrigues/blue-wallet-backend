package com.blue.wallet.controller.transport.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CadastroReceitaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idReceita;

    @NotNull(message = "O campo 'categoriaReceita' não pode ser nulo ou branco.")
    private Integer categoriaReceita;

    @NotBlank(message = "O campo 'descricao' não pode ser nulo ou branco.")
    private String descricao;

    @NotNull(message = "O campo 'numeroParcelas' não pode ser nulo ou branco.")
    private Integer numeroParcelas;

    @NotNull(message = "O campo 'dataPagamento' não pode ser nulo ou branco.")
    private LocalDate dataLancamento;

    @NotNull(message = "O campo 'valor' não pode ser nulo ou branco.")
    private BigDecimal valor;

    @NotNull(message = "O campo 'flgPagamentoEfetuado' não pode ser nulo ou branco.")
    private boolean flgPagamentoEfetuado;

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(Integer categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isFlgPagamentoEfetuado() {
        return flgPagamentoEfetuado;
    }

    public void setFlgPagamentoEfetuado(boolean flgPagamentoEfetuado) {
        this.flgPagamentoEfetuado = flgPagamentoEfetuado;
    }
}
