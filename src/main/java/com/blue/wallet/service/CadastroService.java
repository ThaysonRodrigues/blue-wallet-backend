package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.CadastrarUsuarioRequest;
import com.blue.wallet.domain.UsuarioORM;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository repository;

    public void gravarNovaConta(CadastrarUsuarioRequest request) throws ValidationBusinessException {
        Optional<UsuarioORM> usuario = repository.findByEmail(request.getEmail());

        if(usuario.isPresent()) {
            throw new ValidationBusinessException("Usuário já cadastrado no sistema!");
        }

        createUsuario(request);
    }

    private void createUsuario(CadastrarUsuarioRequest request) {
        UsuarioORM usuario = new UsuarioORM();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCelular(request.getCelular());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setDataCadastro(LocalDate.now());
        usuario.setSenha(new BCryptPasswordEncoder().encode(request.getSenha()));

        repository.save(usuario);
    }
}