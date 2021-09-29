package com.blue.wallet.service;

import com.blue.wallet.domain.LancamentoDespesaORM;
import com.blue.wallet.repository.LancamentoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private LancamentoDespesaRepository repository;

    public LancamentoDespesaORM save(LancamentoDespesaORM despesa) {
        return repository.save(despesa);
    }

    public void deletarDespesaById(Integer despesaId) {
        repository.deleteById(despesaId);
    }

    public List<LancamentoDespesaORM> pesquisarLancamentoPorDataAndUsuario(Integer idUsuario, String data) {
        return repository.findLancamentoPorData(idUsuario, data);
    }
}
