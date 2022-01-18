package com.blue.wallet.controller.transport.response;

import com.blue.wallet.domain.vo.CategoriaDashboardVO;

import java.util.List;

public class DashboardResponseDTO {

    private String valorTotalReceitas;
    private String valorTotalDespesas;
    private String valorSaldoAtual;
    private List<CategoriaDashboardVO> receitas;
    private List<CategoriaDashboardVO> despesas;

    public String getValorTotalReceitas() {
        return valorTotalReceitas;
    }

    public void setValorTotalReceitas(String valorTotalReceitas) {
        this.valorTotalReceitas = valorTotalReceitas;
    }

    public String getValorTotalDespesas() {
        return valorTotalDespesas;
    }

    public void setValorTotalDespesas(String valorTotalDespesas) {
        this.valorTotalDespesas = valorTotalDespesas;
    }

    public String getValorSaldoAtual() {
        return valorSaldoAtual;
    }

    public void setValorSaldoAtual(String valorSaldoAtual) {
        this.valorSaldoAtual = valorSaldoAtual;
    }

    public List<CategoriaDashboardVO> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<CategoriaDashboardVO> receitas) {
        this.receitas = receitas;
    }

    public List<CategoriaDashboardVO> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<CategoriaDashboardVO> despesas) {
        this.despesas = despesas;
    }
}