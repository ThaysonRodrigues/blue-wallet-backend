package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.request.AtualizarDadosUsuarioDTO;
import com.blue.wallet.controller.transport.request.CadastroUsuarioDTO;
import com.blue.wallet.controller.transport.request.VerificarContaDTO;
import com.blue.wallet.domain.UsuarioORM;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CadastroMapper {

    public CadastroUsuarioDTO toCadastroUsuarioRequest(VerificarContaDTO conta) {
        CadastroUsuarioDTO cadastro = new CadastroUsuarioDTO();
        cadastro.setEmail(conta.getEmail());
        cadastro.setNome(conta.getNome());
        cadastro.setSenha(conta.getIdGoogle());

        return cadastro;
    }

    public static UsuarioORM toUsuarioORM(AtualizarDadosUsuarioDTO dadosUsuario, Integer idUsuario) {
        UsuarioORM usuarioORM = new UsuarioORM();

        usuarioORM.setId(idUsuario);
        usuarioORM.setNome(dadosUsuario.getNome());
        usuarioORM.setCelular(dadosUsuario.getCelular());
        usuarioORM.setDataNascimento(dadosUsuario.getDataNascimento());

        return usuarioORM;
    }

    public static UsuarioORM toUsuarioORM(CadastroUsuarioDTO request) {
        UsuarioORM usuario = new UsuarioORM();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCelular(request.getCelular());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setDataCadastro(LocalDate.now());
        usuario.setSenha(new BCryptPasswordEncoder().encode(request.getSenha()));
        usuario.setGoogleCode(request.getGoogleCode());

        return usuario;
    }
}
