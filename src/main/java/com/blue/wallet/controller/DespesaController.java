package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroDespesaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.DespesaURI;
import com.blue.wallet.domain.LancamentoDespesaORM;
import com.blue.wallet.mapper.DespesaMapper;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = DespesaURI.CONTROLLER)
public class DespesaController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private DespesaMapper mapper;

    @PostMapping(value = DespesaURI.CADASTAR)
    public ResponseEntity<?> lancarDespesa(@Valid @RequestBody CadastroDespesaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        CadastroDespesaDTO despesaResponse = null;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            LancamentoDespesaORM despesaORM = despesaService.save(mapper.toLancamentoDespesaORM(request, idUsuario));

            despesaResponse = mapper.toCadastroDespesaDTO(despesaORM);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.status(HttpStatus.OK).body(despesaResponse);
    }

    @PutMapping(value = DespesaURI.EDITAR)
    public ResponseEntity<?> editarDespesa(@Valid @RequestBody CadastroDespesaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        CadastroDespesaDTO despesaResponse = null;

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            LancamentoDespesaORM despesaORM = despesaService.save(mapper.toLancamentoDespesaORM(request, idUsuario));

            despesaResponse = mapper.toCadastroDespesaDTO(despesaORM);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.status(HttpStatus.OK).body(despesaResponse);
    }

    @DeleteMapping(value = DespesaURI.DELETAR)
    public ResponseEntity<?> deletarDespesa(@PathVariable Integer despesaId) {
        try {
            despesaService.deletarDespesaById(despesaId);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}