package com.blue.wallet.service;

import com.blue.wallet.controller.transport.request.AtualizarSenhaDTO;
import com.blue.wallet.domain.UsuarioORM;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.repository.UsuarioRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class RecuperarSenhaService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService;

    public void enviarCodigoRecuperacaoSenha(String email) throws ValidationBusinessException {
        Optional<UsuarioORM> usuario = repository.findByEmail(email);

        if(!usuario.isPresent()) {
            throw new ValidationBusinessException("E-mail informado não encontrado");
        }

        Integer codigoVerificacaoEmail = gerarCodigoVerificacaoEmail();

        usuario.get().setCodigoAlteracaoSenha(codigoVerificacaoEmail);
        usuario.get().setDataSolicitacaoAlterarSenha(LocalDate.now());

        repository.save(usuario.get());

        emailService.sendEmailRecuperarSenha(usuario.get(), codigoVerificacaoEmail);
    }

    public void atualizarSenhaCadastro(AtualizarSenhaDTO atualizarSenha) throws ValidationBusinessException {
        UsuarioORM usuario = getUsuarioByEmailAndCodigoVerificacao(atualizarSenha.getEmail(), atualizarSenha.getCodigo());

        usuario.setSenha(new BCryptPasswordEncoder().encode(atualizarSenha.getSenha()));

        repository.save(usuario);
    }

    public boolean codigoRecuperacaoSenhaVerificado(String email, Integer codigo) {
        Optional<UsuarioORM> usuario = repository.findByEmailAndCodigoAlteracaoSenha(email, codigo);

        if (usuario.isPresent()) {
            return true;
        }

        return false;
    }

    private Integer gerarCodigoVerificacaoEmail() {
        Random r = new Random();
        return r.nextInt(9999) + 0001;
    }

    private UsuarioORM getUsuarioByEmailAndCodigoVerificacao(String email, Integer codigo) throws ValidationBusinessException {
        Optional<UsuarioORM> usuario = repository.findByEmailAndCodigoAlteracaoSenha(email, codigo);

        if(!usuario.isPresent()) {
            throw new ValidationBusinessException("E-mail ou código de verificação informado não encontrado!");
        }

        return usuario.get();
    }
}
