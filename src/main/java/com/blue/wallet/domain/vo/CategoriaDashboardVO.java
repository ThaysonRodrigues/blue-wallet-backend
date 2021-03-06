package com.blue.wallet.domain.vo;

import java.math.BigDecimal;

public class CategoriaDashboardVO {

    private BigDecimal valor;
    private String descricao;

    public CategoriaDashboardVO() {}

    public CategoriaDashboardVO(BigDecimal valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
