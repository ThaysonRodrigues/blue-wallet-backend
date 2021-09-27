package com.blue.wallet.service;

import com.blue.wallet.domain.LancamentoReceitaORM;
import com.blue.wallet.repository.LancamentoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private LancamentoReceitaRepository repository;

    public LancamentoReceitaORM save(LancamentoReceitaORM lancamentoReceita) {
        return repository.save(lancamentoReceita);
    }

    public void deletarReceitaById(Integer receitaId) {
        repository.deleteById(receitaId);
    }
}
