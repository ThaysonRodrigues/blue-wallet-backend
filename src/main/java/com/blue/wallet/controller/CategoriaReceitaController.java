package com.blue.wallet.controller;

import com.blue.wallet.controller.transport.request.CategoriaDTO;
import com.blue.wallet.controller.uri.CategoriaReceitaURI;
import com.blue.wallet.domain.CategoriaReceitaORM;
import com.blue.wallet.mapper.CategoriaMapper;
import com.blue.wallet.service.CategoriaReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CategoriaReceitaURI.CONTROLLER)
public class CategoriaReceitaController {

    @Autowired
    private CategoriaReceitaService service;

    @Autowired
    private CategoriaMapper mapper;

    @GetMapping
    public ResponseEntity<?> findCategoriaReceita(@RequestHeader("Authorization") String token) {
        List<CategoriaReceitaORM> listCategoriaReceita = service.findCategoriaReceita();

        if(listCategoriaReceita.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<CategoriaDTO> listCategoriaDTO = mapper.toCategoriaReceitaDTO(listCategoriaReceita);


        return ResponseEntity.status(HttpStatus.OK).body(listCategoriaDTO);
    }
}
