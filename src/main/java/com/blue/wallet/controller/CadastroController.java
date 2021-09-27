package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroUsuarioRequest;
import com.blue.wallet.controller.transport.request.VerificarContaRequest;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.CadastroURI;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping(value = CadastroURI.CONTROLLER)
public class CadastroController {

    @Autowired
    private CadastroService service;

    @PostMapping(value = CadastroURI.CADASTAR)
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid CadastroUsuarioRequest request, BindingResult result) {
        Response response = new Response();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            service.gravarNovaConta(request);
        } catch (ValidationBusinessException e) {
            return ResponseBodyHelper.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = CadastroURI.VERIFICAR)
    public ResponseEntity<?> verificaSeExisteConta(@RequestBody @Valid VerificarContaRequest request, BindingResult result) {
        Response response = new Response();
        boolean existeCadastro;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            existeCadastro = service.existeContaCadastrada(request);
        }  catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        if(existeCadastro) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }
}