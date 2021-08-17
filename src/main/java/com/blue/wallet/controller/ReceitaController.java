package com.blue.wallet.controller;

import com.blue.wallet.controller.transport.request.CadastrarUsuarioRequest;
import com.blue.wallet.controller.uri.CadastroURI;
import com.blue.wallet.controller.uri.ReceitaURI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ReceitaURI.CONTROLLER)
public class ReceitaController {

    @PostMapping(value = ReceitaURI.CADASTAR)
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioRequest request, BindingResult result) {
        return null;
    }
}
