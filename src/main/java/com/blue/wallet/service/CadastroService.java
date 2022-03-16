package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.CadastroUsuarioRequest;
import com.blue.wallet.controller.transport.request.VerificarContaRequest;
import com.blue.wallet.controller.transport.response.UserDTO;
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

    public void gravarNovaConta(CadastroUsuarioRequest request) throws ValidationBusinessException {
        Optional<UsuarioORM> usuario = repository.findByEmail(request.getEmail());

        if(usuario.isPresent()) {
            throw new ValidationBusinessException("Email já cadastrado no sistema!");
        }

        createUsuario(request);
    }

    public boolean existeContaCadastrada(String email) {
        Optional<UsuarioORM> usuario = repository.findByEmail(email);

        if(usuario.isPresent()) {
            return true;
        }

        return false;
    }

    public UserDTO getUsername(Integer idUsuario) {
       return new UserDTO(repository.getUsername(idUsuario).trim());
    }

    private void createUsuario(CadastroUsuarioRequest request) {
        UsuarioORM usuario = new UsuarioORM();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCelular(request.getCelular());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setDataCadastro(LocalDate.now());
        usuario.setSenha(new BCryptPasswordEncoder().encode(request.getSenha()));
        usuario.setGoogleCode(request.getGoogleCode());

        repository.save(usuario);
    }
}