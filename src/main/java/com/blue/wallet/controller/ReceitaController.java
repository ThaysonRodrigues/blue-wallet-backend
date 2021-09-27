package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.DespesaURI;
import com.blue.wallet.controller.uri.ReceitaURI;
import com.blue.wallet.domain.LancamentoReceitaORM;
import com.blue.wallet.mapper.ReceitaMapper;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ReceitaURI.CONTROLLER)
public class ReceitaController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ReceitaMapper mapper;

    @PostMapping(value = ReceitaURI.CADASTAR)
    public ResponseEntity<?> lancarReceita(@Valid @RequestBody CadastroReceitaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        CadastroReceitaDTO receitaResponse = null;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            LancamentoReceitaORM receitaORM = receitaService.save(mapper.toLancamentoReceitaORM(request, idUsuario));

            receitaResponse = mapper.toCadastroReceitaDTO(receitaORM);

        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(receitaResponse);
    }

    @PutMapping(value = ReceitaURI.EDITAR)
    public ResponseEntity<?> editarReceita(@Valid @RequestBody CadastroReceitaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        CadastroReceitaDTO receitaResponse = null;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            LancamentoReceitaORM receitaORM = receitaService.save(mapper.toLancamentoReceitaORM(request, idUsuario));

            receitaResponse = mapper.toCadastroReceitaDTO(receitaORM);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.status(HttpStatus.OK).body(receitaResponse);
    }

    @DeleteMapping(value = ReceitaURI.DELETAR)
    public ResponseEntity<?> deletarReceita(@PathVariable Integer receitaId) {
        try {
            receitaService.deletarReceitaById(receitaId);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
