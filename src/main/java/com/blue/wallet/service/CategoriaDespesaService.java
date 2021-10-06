package com.blue.wallet.service;

import com.blue.wallet.domain.CategoriaDespesaORM;
import com.blue.wallet.repository.CategoriaDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaDespesaService {

    @Autowired
    private CategoriaDespesaRepository repository;

    public List<CategoriaDespesaORM> findCategoriaReceita() {
        return repository.findCategoriaDespesa();
    }
}
