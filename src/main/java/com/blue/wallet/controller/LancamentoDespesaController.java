package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroDespesaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.LancamentoDespesaURI;
import com.blue.wallet.controller.uri.LancamentoReceitaURI;
import com.blue.wallet.domain.LancamentoDespesaORM;
import com.blue.wallet.mapper.DespesaMapper;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.DespesaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping(value = LancamentoDespesaURI.EDITAR)
    @ApiOperation(value = "EndPoint para editar uma despesa cadastrada")
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
    public ResponseEntity<?> pesquisarLancamentoReceitaPorData(@RequestHeader("Authorization") String token,
                                                               @PathVariable String data) {
        String strToken = tokenUtil.cleanToken(token);

        String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

        List<LancamentoDespesaORM> lancamentoPorData = despesaService.pesquisarLancamentoPorDataAndUsuario(Integer
                .parseInt(idUsuario), data);

        List<CadastroDespesaDTO> despesasDTO = mapper.toListCadastroDespesaDTO(lancamentoPorData);

        if(despesasDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(despesasDTO);
    }
}