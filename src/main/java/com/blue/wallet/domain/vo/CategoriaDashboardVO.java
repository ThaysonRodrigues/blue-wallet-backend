package com.blue.wallet.domain.vo;

import java.math.BigDecimal;

public class CategoriaDashboardVO {

    private BigDecimal valor;
    private String strValor;
    private long quantidade;
    private String descricao;

    public CategoriaDashboardVO() {}

    public CategoriaDashboardVO(BigDecimal valor, long quantidade, String descricao) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStrValor() {
        return strValor;
    }

    public void setStrValor(String strValor) {
        this.strValor = strValor;
    }
}
