package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroDespesaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.LancamentoDespesaURI;
import com.blue.wallet.controller.uri.LancamentoReceitaURI;
import com.blue.wallet.mapper.DespesaMapper;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.DespesaService;
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
@RequestMapping(value = LancamentoDespesaURI.CONTROLLER)
public class LancamentoDespesaController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private DespesaMapper mapper;

    @PostMapping(value = LancamentoDespesaURI.CADASTAR)
    @ApiOperation(value = "EndPoint para cadastrar uma nova despesa")
    public ResponseEntity lancarDespesa(@Valid @RequestBody CadastroDespesaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

            despesaService.save(request, idUsuario);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.ok().build();
    }

    @PutMapping(value = LancamentoDespesaURI.EDITAR)
    @ApiOperation(value = "EndPoint para editar uma despesa cadastrada")
    public ResponseEntity<?> editarDespesa(@Valid @RequestBody CadastroDespesaDTO request,
                                           @RequestHeader("Authorization") String token, BindingResult result) {
        Response response = new Response();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            String strToken = tokenUtil.cleanToken(token);

            String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

           despesaService.edit(request, idUsuario);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return  ResponseEntity.ok().build();
    }

    @DeleteMapping(value = LancamentoDespesaURI.DELETAR)
    @ApiOperation(value = "EndPoint para deletar uma despesa")
    public ResponseEntity<?> deletarDespesa(@PathVariable Integer despesaId) {
        try {
            despesaService.deletarDespesaById(despesaId);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = LancamentoReceitaURI.PESQUISAR_POR_DATA)
    @ApiOperation(value = "EndPoint para pesquisar despesas por data e usuário")
    public ResponseEntity<Page<CadastroDespesaDTO>> pesquisarLancamentoReceitaPorData(Pageable pageable,
                                                                                      @RequestHeader("Authorization") String token,
                                                                                      @PathVariable String data) {
        String idUsuario = tokenUtil.getIdUsuariofromToken(tokenUtil.cleanToken(token));

        Page<CadastroDespesaDTO> lancamentoPorData = despesaService.pesquisarLancamentoPorDataAndUsuario(Integer
                .parseInt(idUsuario), data, pageable);

        if(lancamentoPorData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(lancamentoPorData);
    }
}