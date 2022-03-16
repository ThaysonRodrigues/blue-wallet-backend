package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroUsuarioRequest;
import com.blue.wallet.controller.transport.request.VerificarContaRequest;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.transport.response.UserDTO;
import com.blue.wallet.controller.uri.CadastroURI;
import com.blue.wallet.exceptions.ValidationBusinessException;
import com.blue.wallet.mapper.CadastroMapper;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.security.dto.JwtRequest;
import com.blue.wallet.security.dto.JwtResponse;
import com.blue.wallet.service.CadastroService;
import com.blue.wallet.service.JwtAuthenticationService;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private CadastroMapper mapper;

    @Autowired
    private JwtAuthenticationService jwtService;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @PostMapping(value = CadastroURI.CADASTAR)
    @ApiOperation(value = "EndPoint para cadastrar um novo usuário")
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
    @ApiOperation(value = "EndPoint para verificar se email já está cadastrado")
    public ResponseEntity<?> verificaSeExisteConta(@RequestBody @Valid VerificarContaRequest request, BindingResult result) throws Exception {
        Response response = new Response();
        boolean existeCadastro;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            existeCadastro = service.existeContaCadastrada(request.getEmail());
        }  catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        if(!existeCadastro) {
            service.gravarNovaConta(mapper.toCadastroUsuarioRequest(request));
        }

        JwtResponse token = jwtService.autentication(new JwtRequest(request.getEmail(), request.getIdGoogle()));

        return ResponseEntity.ok().body(token);
    }

    @GetMapping(value = CadastroURI.USER_NAME)
    @ApiOperation(value = "EndPoint para retornar dados do cliente")
    public ResponseEntity<?> getUsername(@RequestHeader("Authorization") String token) {
        try {
            String idUsuario = tokenUtil.getIdUsuariofromToken(tokenUtil.cleanToken(token));

            UserDTO response = service.getUsername(Integer.parseInt(idUsuario));

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }
    }
}