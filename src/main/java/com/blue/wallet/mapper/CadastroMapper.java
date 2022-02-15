package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.request.CadastroUsuarioRequest;
import com.blue.wallet.controller.transport.request.VerificarContaRequest;
import org.springframework.stereotype.Component;

@Component
public class CadastroMapper {

    public CadastroUsuarioRequest toCadastroUsuarioRequest(VerificarContaRequest conta) {
        CadastroUsuarioRequest cadastro = new CadastroUsuarioRequest();
        cadastro.setEmail(conta.getEmail());
        cadastro.setNome(conta.getNome());
        cadastro.setSenha(conta.getIdGoogle());

        return cadastro;
    }
}
