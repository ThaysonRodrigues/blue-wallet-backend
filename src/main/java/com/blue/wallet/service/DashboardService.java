package com.blue.wallet.service;

import com.blue.wallet.controller.transport.response.DashboardResponseDTO;
import com.blue.wallet.domain.vo.CategoriaDashboardVO;
import com.blue.wallet.mapper.DashboardMapper;
import com.blue.wallet.repository.LancamentoDespesaRepository;
import com.blue.wallet.repository.LancamentoReceitaRepository;
import com.blue.wallet.repository.entitymanager.DashboardEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private LancamentoReceitaRepository receitaRepository;

    @Autowired
    private LancamentoDespesaRepository despesaRepository;
    
    @Autowired
    private DashboardEntityManager dashboardEntityManager;

    @Autowired
    private DashboardMapper dashboardMapper;

    public DashboardResponseDTO pesquisarInformacoesDashboard(Integer idUsuario, String data) {
        BigDecimal vlrReceitas = receitaRepository.calculaReceitaPorData(idUsuario, data);
        BigDecimal vlrDespesas = despesaRepository.calculaDespesaPorData(idUsuario, data);

        if(vlrReceitas == null)
            vlrReceitas = new BigDecimal(0);

        if(vlrDespesas == null)
            vlrDespesas = new BigDecimal(0);

        BigDecimal vlrSaldo = vlrReceitas.subtract(vlrDespesas);

        List<CategoriaDashboardVO> receitas = dashboardEntityManager
                .getCalculoReceitaPorCategoria(idUsuario, data);

        List<CategoriaDashboardVO> despesas = dashboardEntityManager
                .getCalculoDespesaPorCategoria(idUsuario, data);

        return dashboardMapper.toDashboardResponseDTO(vlrReceitas, vlrDespesas, vlrSaldo, receitas, despesas);
    }
}
