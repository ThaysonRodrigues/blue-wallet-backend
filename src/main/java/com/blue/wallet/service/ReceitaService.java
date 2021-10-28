package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.domain.LancamentoReceitaORM;
import com.blue.wallet.mapper.ReceitaMapper;
import com.blue.wallet.repository.LancamentoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private LancamentoReceitaRepository repository;

    @Autowired
    private ReceitaMapper receitaMapper;

    public LancamentoReceitaORM save(LancamentoReceitaORM lancamentoReceita) {
        return repository.save(lancamentoReceita);
    }

    public void deletarReceitaById(Integer receitaId) {
        repository.deleteById(receitaId);
    }

    public Page<CadastroReceitaDTO> pesquisarLancamentoPorDataAndUsuario(Integer idUsuario, String data, Pageable pageable) {
        return repository.findLancamentoPorData(idUsuario, data, pageable)
                .map(c -> receitaMapper.toCadastroReceitaDTO(c));
    }
}
