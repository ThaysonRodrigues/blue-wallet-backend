package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.AtualizarDadosUsuarioDTO;
import com.blue.wallet.controller.transport.request.CadastroUsuarioDTO;
import com.blue.wallet.controller.transport.response.UserDTO;
import com.blue.wallet.domain.UsuarioORM;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.mapper.CadastroMapper;
import com.blue.wallet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository repository;

    public void gravarNovaConta(CadastroUsuarioDTO request) throws ValidationBusinessException {
        Optional<UsuarioORM> usuario = repository.findByEmail(request.getEmail());

        if(usuario.isPresent()) {
            throw new ValidationBusinessException("Email já cadastrado no sistema!");
        }

        repository.save(CadastroMapper.toUsuarioORM(request));
    }

    @Transactional
    public void atualidarDadosUsuario(AtualizarDadosUsuarioDTO dadosUsuario, Integer idUsuario) {
        repository.atualizarDadosCadastrais(dadosUsuario.getNome(), dadosUsuario.getCelular(), dadosUsuario
                .getDataNascimento(), idUsuario);
    }

    public boolean existeContaCadastrada(String email) {
        Optional<UsuarioORM> usuario = repository.findByEmail(email);

        if(usuario.isPresent()) {
            return true;
        }

        return false;
    }

    public UserDTO getDadosCadastrais(Integer idUsuario) {
        UsuarioORM usuarioORM = repository.getUser(idUsuario);

        UserDTO user = new UserDTO();
        user.setNome(usuarioORM.getNome().trim());
        user.setCelular(usuarioORM.getCelular());
        user.setEmail(usuarioORM.getEmail());
        user.setDataNascimento(usuarioORM.getDataNascimento());

        return user;
    }
}