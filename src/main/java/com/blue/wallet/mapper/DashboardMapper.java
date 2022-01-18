package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.response.DashboardResponseDTO;
import com.blue.wallet.domain.vo.CategoriaDashboardVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Component
public class DashboardMapper {

    private static final String MOEDA_BRL = "R$ ";

    public DashboardResponseDTO toDashboardResponseDTO(BigDecimal vlrReceitas, BigDecimal vlrDespesas
            , BigDecimal vlrSaldo, List<CategoriaDashboardVO> receitas, List<CategoriaDashboardVO> despesas) {
        DashboardResponseDTO dashboardResponse = new DashboardResponseDTO();
        dashboardResponse.setValorSaldoAtual(formatCurrency(vlrSaldo));
        dashboardResponse.setValorTotalDespesas(formatCurrency(vlrDespesas));
        dashboardResponse.setValorTotalReceitas(formatCurrency(vlrReceitas));
        dashboardResponse.setReceitas(setValorMonetarioCategoria(receitas));
        dashboardResponse.setDespesas(setValorMonetarioCategoria(despesas));

        return dashboardResponse;
    }

    private List<CategoriaDashboardVO> setValorMonetarioCategoria(List<CategoriaDashboardVO> categorias) {
        for(int c = 0; c < categorias.size(); c ++) {
            categorias.get(c).setStrValor(formatCurrency(categorias.get(c).getValor()));
        }

        return categorias;
    }

    private String formatCurrency(BigDecimal valor) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

        String valorCategoria = nf.format(valor);

        if(!valorCategoria.contains(",")) {
            valorCategoria = valorCategoria + ",00";
        }

        return MOEDA_BRL + valorCategoria;
    }
}