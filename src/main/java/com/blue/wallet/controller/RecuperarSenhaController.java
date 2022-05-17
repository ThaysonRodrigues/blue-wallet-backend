package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.AtualizarSenhaDTO;
import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.RecuperarSenhaURI;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.service.RecuperarSenhaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RecuperarSenhaURI.CONTROLLER)
public class RecuperarSenhaController {

    @Autowired
    private RecuperarSenhaService service;

    @PostMapping(value = RecuperarSenhaURI.ENVIAR_EMAIL)
    @ApiOperation(value = "EndPoint para recuperação de senha")
    public ResponseEntity<?> enviarCodigoVerificacao(@PathVariable String email) {
        try {
            service.enviarCodigoRecuperacaoSenha(email);
        } catch (ValidationBusinessException e) {
            return ResponseBodyHelper.notFound(e.getMessage());
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = RecuperarSenhaURI.ATUALIZAR_SENHA)
    public ResponseEntity<?> atualizarSenha(@Valid @RequestBody AtualizarSenhaDTO request, BindingResult result) {
        Response response = new Response();

        try {
            if (result.hasErrors()) {
                result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            service.atualizarSenhaCadastro(request);

        } catch (ValidationBusinessException e) {
            return ResponseBodyHelper.unprocessableEntity(e.getMessage());
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = RecuperarSenhaURI.VERIFICAR)
    @ApiOperation(value = "EndPoint para verificação do código de recuperação de senha")
    public ResponseEntity<?> verificarCodigo(@RequestHeader("email") String email, @RequestHeader("codigo") Integer codigo) {
        try {
           boolean codigoVerificado =  service.codigoRecuperacaoSenhaVerificado(email, codigo);

           if (codigoVerificado) {
               return ResponseEntity.ok().build();
           }

        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.unprocessableEntity().build();
    }
}