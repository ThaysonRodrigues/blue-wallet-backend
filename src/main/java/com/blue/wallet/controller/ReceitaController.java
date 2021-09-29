package com.blue.wallet.controller;

import com.blue.wallet.controller.helper.ResponseBodyHelper;
import com.blue.wallet.controller.transport.request.CadastroReceitaDTO;
import com.blue.wallet.controller.transport.response.Response;
import com.blue.wallet.controller.uri.DespesaURI;
import com.blue.wallet.controller.uri.ReceitaURI;
import com.blue.wallet.domain.LancamentoReceitaORM;
import com.blue.wallet.mapper.ReceitaMapper;
import com.blue.wallet.repository.LancamentoReceitaRepository;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.ReceitaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = ReceitaURI.CONTROLLER)
public class ReceitaController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ReceitaMapper mapper;

    @Autowired
    private LancamentoReceitaRepository repository;

    @PostMapping(value = ReceitaURI.CADASTAR)
    @ApiOperation(value = "EndPoint para cadastrar uma nova receita")
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
    @ApiOperation(value = "EndPoint para editar uma receita cadastrada")
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
    @ApiOperation(value = "EndPoint para deletar uma receita")
    public ResponseEntity<?> deletarReceita(@PathVariable Integer receitaId) {
        try {
            receitaService.deletarReceitaById(receitaId);
        } catch (Exception e) {
            return ResponseBodyHelper.internalServerError(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = ReceitaURI.PESQUISAR_POR_DATA)
    @ApiOperation(value = "EndPoint para pesquisar receitas por data e usuário")
    public ResponseEntity<?> pesquisarLancamentoReceitaPorData(@RequestHeader("Authorization") String token,
                                                               @PathVariable String data) {
        String strToken = tokenUtil.cleanToken(token);

        String idUsuario = tokenUtil.getIdUsuariofromToken(strToken);

        List<LancamentoReceitaORM> lancamentoPorData = receitaService.pesquisarLancamentoPorDataAndUsuario(Integer
                .parseInt(idUsuario), data);

        List<CadastroReceitaDTO> receitasDTO = mapper.toListCadastroReceitaDTO(lancamentoPorData);

        if(receitasDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(receitasDTO);
    }
}