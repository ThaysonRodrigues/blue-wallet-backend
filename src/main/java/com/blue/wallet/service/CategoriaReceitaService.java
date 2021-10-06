package com.blue.wallet.service;

import com.blue.wallet.domain.CategoriaReceitaORM;
import com.blue.wallet.repository.CategoriaReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaReceitaService {

    @Autowired
    private CategoriaReceitaRepository repository;

    public List<CategoriaReceitaORM> findCategoriaReceita() {
        return repository.findCategoriaReceita();
    }
}
