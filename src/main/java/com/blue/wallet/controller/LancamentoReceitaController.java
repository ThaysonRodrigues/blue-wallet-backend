package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.LancamentoReceitaURI;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.ReceitaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = LancamentoReceitaURI.CONTROLLER)
public class LancamentoReceitaController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private ReceitaService receitaService;

    @PostMapping(value = LancamentoReceitaURI.CADASTAR)
    @ApiOperation(value = "EndPoint para cadastrar uma nova receita")
    public ResponseEntity lancarReceita(@Valid @RequestBody CadastroReceitaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            receitaService.save(request, idUsuario);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = LancamentoReceitaURI.EDITAR)
    @ApiOperation(value = "EndPoint para editar uma receita cadastrada")
    public ResponseEntity<?> editarReceita(@Valid @RequestBody CadastroReceitaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            receitaService.edit(request, idUsuario);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = LancamentoReceitaURI.DELETAR)
    @ApiOperation(value = "EndPoint para deletar uma receita")
    public ResponseEntity<?> deletarReceita(@PathVariable Integer receitaId) {
        try {
            receitaService.deletarReceitaById(receitaId);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = LancamentoReceitaURI.PESQUISAR_POR_DATA)
    @ApiOperation(value = "EndPoint para pesquisar receitas por data e usuário")
    public ResponseEntity<Page<CadastroReceitaDTO>> pesquisarLancamentoReceitaPorData(Pageable pageable,
                                                                                      @RequestHeader("Authorization") String token,
                                                                                      @PathVariable String data) {
        String idUsuario = tokenUtil.getIdUsuariofromToken(tokenUtil.cleanToken(token));

        Page<CadastroReceitaDTO> lancamentoPorData = receitaService.pesquisarLancamentoPorDataAndUsuario(Integer
                .parseInt(idUsuario), data, pageable);

        if (lancamentoPorData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(lancamentoPorData);
    }
}