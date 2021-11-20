package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.domain.CategoriaReceitaORM;
import com.blue.wallet.domain.LancamentoReceitaORM;
import com.blue.wallet.domain.UsuarioORM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceitaMapper {

    public LancamentoReceitaORM toLancamentoReceitaORM(CadastroReceitaDTO receitaRequest, String idUsuario) {
        LancamentoReceitaORM lancamentoReceita = new LancamentoReceitaORM();

        lancamentoReceita.setId(receitaRequest.getIdReceita());
        lancamentoReceita.setUsuario(new UsuarioORM(Integer.parseInt(idUsuario)));
        lancamentoReceita.setDescricao(receitaRequest.getDescricao());
        lancamentoReceita.setNumeroParcelas(receitaRequest.getNumeroParcelas());
        lancamentoReceita.setValor(receitaRequest.getValor());
        lancamentoReceita.setDataLancamento(receitaRequest.getDataLancamento());
        lancamentoReceita.setFlgPagamentoEfetuado(receitaRequest.isFlgPagamentoEfetuado());
        lancamentoReceita.setCategoriaReceita(new CategoriaReceitaORM(receitaRequest.getCategoriaReceita()));

        return lancamentoReceita;
    }

    public CadastroReceitaDTO toCadastroReceitaDTO(LancamentoReceitaORM receitaORM) {
        CadastroReceitaDTO cadastroReceita = new CadastroReceitaDTO();

        cadastroReceita.setIdReceita(receitaORM.getId());
        cadastroReceita.setDescricao(receitaORM.getDescricao());
        cadastroReceita.setNumeroParcelas(receitaORM.getNumeroParcelas());
        cadastroReceita.setValor(receitaORM.getValor());
        cadastroReceita.setDataLancamento(receitaORM.getDataLancamento());
        cadastroReceita.setFlgPagamentoEfetuado(receitaORM.isFlgPagamentoEfetuado());
        cadastroReceita.setCategoriaReceita(receitaORM.getCategoriaReceita().getId());
        cadastroReceita.setDescricaoCategoriaReceita(receitaORM.getCategoriaReceita().getDescricao());

        return cadastroReceita;
    }

    public List<CadastroReceitaDTO> toListCadastroReceitaDTO(List<LancamentoReceitaORM> lancamentoPorData) {

        List<CadastroReceitaDTO> receitas = new ArrayList<>();

        for (LancamentoReceitaORM lancamento: lancamentoPorData) {
            CadastroReceitaDTO receitaDTO = new CadastroReceitaDTO();

            receitaDTO.setIdReceita(lancamento.getId());
            receitaDTO.setCategoriaReceita(lancamento.getCategoriaReceita().getId());
            receitaDTO.setDescricao(lancamento.getDescricao());
            receitaDTO.setNumeroParcelas(lancamento.getNumeroParcelas());
            receitaDTO.setDataLancamento(lancamento.getDataLancamento());
            receitaDTO.setValor(lancamento.getValor());
            receitaDTO.setFlgPagamentoEfetuado(lancamento.isFlgPagamentoEfetuado());
            receitaDTO.setDescricaoCategoriaReceita(lancamento.getCategoriaReceita().getDescricao());

            receitas.add(receitaDTO);
        }

        return receitas;
    }
}