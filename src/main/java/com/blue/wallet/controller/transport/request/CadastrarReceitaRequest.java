package com.blue.wallet.controller.transport.request;

import javax.validation.constraints.NotNull;

public class CadastrarReceitaRequest {

    @NotNull(message = "O campo 'id_usuario' não pode ser nulo ou branco.")
    private Integer id_usuario;

    @NotNull(message = "O campo 'categoria_receita' não pode ser nulo ou branco.")
    private Integer categoria_receita;

    @NotNull(message = "O campo 'descricao' não pode ser nulo ou branco.")
    private String descricao;

    @NotNull(message = "O campo 'numero_parcelas' não pode ser nulo ou branco.")
    private Integer numero_parcelas;

    @NotNull(message = "O campo 'valor' não pode ser nulo ou branco.")
    private float valor;

    @NotNull(message = "O campo 'flg_pagamento_efetuado' não pode ser nulo ou branco.")
    private boolean flg_pagamento_efetuado;
}
