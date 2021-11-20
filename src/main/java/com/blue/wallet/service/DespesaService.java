package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.CadastroDespesaDTO;
import com.blue.wallet.domain.LancamentoDespesaORM;
import com.blue.wallet.mapper.DespesaMapper;
import com.blue.wallet.repository.LancamentoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private LancamentoDespesaRepository repository;

    @Autowired
    private DespesaMapper mapper;

    public void save(CadastroDespesaDTO despesaDTO, String idUsuario) {
        int numParcelas = despesaDTO.getNumeroParcelas();

        for (int cont = 0; cont < numParcelas; cont++) {
            LancamentoDespesaORM lancamentoDespesaORM = mapper.toLancamentoDespesaORM(despesaDTO, idUsuario);
            lancamentoDespesaORM.setNumeroParcelas(cont + 1);

            if(cont > 0) {
                lancamentoDespesaORM.setDataPagamento(lancamentoDespesaORM.getDataPagamento().plusMonths(cont));
                lancamentoDespesaORM.setFlgPagamentoEfetuado(false);
            }

            repository.save(lancamentoDespesaORM);
        }
    }

    public void edit(CadastroDespesaDTO despesaDTO, String idUsuario) {
        LancamentoDespesaORM lancamentoDespesaORM = mapper.toLancamentoDespesaORM(despesaDTO, idUsuario);

        repository.save(lancamentoDespesaORM);
    }

    public void deletarDespesaById(Integer despesaId) {
        repository.deleteById(despesaId);
    }

    public Page<CadastroDespesaDTO> pesquisarLancamentoPorDataAndUsuario(Integer idUsuario, String data, Pageable pageable) {
        return repository.findLancamentoPorData(idUsuario, data, pageable).map(c -> mapper.toCadastroDespesaDTO(c));
    }
}
