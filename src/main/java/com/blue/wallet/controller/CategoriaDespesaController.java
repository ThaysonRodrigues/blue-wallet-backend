package com.blue.wallet.controller;

import com.blue.wallet.controller.transport.request.CategoriaDTO;
import com.blue.wallet.controller.uri.CategoriaDespesaURI;
import com.blue.wallet.domain.CategoriaDespesaORM;
import com.blue.wallet.mapper.CategoriaMapper;
import com.blue.wallet.service.CategoriaDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CategoriaDespesaURI.CONTROLLER)
public class CategoriaDespesaController {

    @Autowired
    private CategoriaDespesaService service;

    @Autowired
    private CategoriaMapper mapper;

    @GetMapping
    public ResponseEntity<?> findCategoriaDespesa(@RequestHeader("Authorization") String token) {
        List<CategoriaDespesaORM> listCategoriaDespesa = service.findCategoriaReceita();

        if(listCategoriaDespesa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<CategoriaDTO> listCategoriaDTO = mapper.toCategoriaDespesaDTO(listCategoriaDespesa);


        return ResponseEntity.status(HttpStatus.OK).body(listCategoriaDTO);
    }
}
