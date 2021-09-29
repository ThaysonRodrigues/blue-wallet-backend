package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.request.CadastroDespesaDTO;
import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DespesaMapper {

    public LancamentoDespesaORM toLancamentoDespesaORM(CadastroDespesaDTO despesaRequest, String idUsuario) {
        LancamentoDespesaORM lancamentoDespesa = new LancamentoDespesaORM();

        lancamentoDespesa.setId(despesaRequest.getIdDespesa());
        lancamentoDespesa.setUsuario(new UsuarioORM(Integer.parseInt(idUsuario)));
        lancamentoDespesa.setDescricao(despesaRequest.getDescricao());
        lancamentoDespesa.setNumeroParcelas(despesaRequest.getNumeroParcelas());
        lancamentoDespesa.setValor(despesaRequest.getValor());
        lancamentoDespesa.setDataPagamento(despesaRequest.getDataPagamento());
        lancamentoDespesa.setFlgPagamentoEfetuado(despesaRequest.isFlgPagamentoEfetuado());
        lancamentoDespesa.setCategoriaDespesa(new CategoriaDespesaORM(despesaRequest.getCategoriaDespesa()));

        return lancamentoDespesa;
    }

    public CadastroDespesaDTO toCadastroDespesaDTO(LancamentoDespesaORM lancamentoDespesa) {
        CadastroDespesaDTO despesa = new CadastroDespesaDTO();

        despesa.setIdDespesa(lancamentoDespesa.getId());
        despesa.setDescricao(lancamentoDespesa.getDescricao());
        despesa.setCategoriaDespesa(lancamentoDespesa.getCategoriaDespesa().getId());
        despesa.setNumeroParcelas(lancamentoDespesa.getNumeroParcelas());
        despesa.setValor(lancamentoDespesa.getValor());
        despesa.setDataPagamento(lancamentoDespesa.getDataPagamento());
        despesa.setFlgPagamentoEfetuado(lancamentoDespesa.isFlgPagamentoEfetuado());

        return despesa;
    }

    public List<CadastroDespesaDTO> toListCadastroDespesaDTO(List<LancamentoDespesaORM> lancamentoPorData) {
        List<CadastroDespesaDTO> despesas = new ArrayList<>();

        for (LancamentoDespesaORM lancamento: lancamentoPorData) {
            CadastroDespesaDTO despesaDTO = new CadastroDespesaDTO();

            despesaDTO.setIdDespesa(lancamento.getId());
            despesaDTO.setCategoriaDespesa(lancamento.getCategoriaDespesa().getId());
            despesaDTO.setDescricao(lancamento.getDescricao());
            despesaDTO.setNumeroParcelas(lancamento.getNumeroParcelas());
            despesaDTO.setDataPagamento(lancamento.getDataPagamento());
            despesaDTO.setValor(lancamento.getValor());
            despesaDTO.setFlgPagamentoEfetuado(lancamento.isFlgPagamentoEfetuado());

            despesas.add(despesaDTO);
        }

        return despesas;
    }
}
