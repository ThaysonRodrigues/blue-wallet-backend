package com.blue.wallet.service;

import com.blue.wallet.domain.UsuarioORM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("spring.mail.username")
    private String emailFrom;

    private static final String ASSUTO = "Recuperação de senha BLUE WALLET";

    public void sendEmailRecuperarSenha(UsuarioORM usuario, Integer codVerificacao) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(usuario.getEmail());
        message.setSubject(ASSUTO);
        message.setText(getMensagem(usuario, codVerificacao));

        emailSender.send(message);
    }

    private String getMensagem(UsuarioORM usuario, Integer codVerificacao) {
        return "Olá " + usuario.getNome().trim() + " recebemos uma solicitação para recuperação de senha, valide o código" +
                "  abaixo para recuperar sua conta de acesso no blue wallet.\n" +
                "Código de acesso: "+ codVerificacao + "\n" +
                "Obrigado!";
    }
}
