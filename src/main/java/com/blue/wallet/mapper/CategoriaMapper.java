package com.blue.wallet.mapper;

import com.blue.wallet.controller.transport.request.CategoriaDTO;
import com.blue.wallet.domain.CategoriaDespesaORM;
import com.blue.wallet.domain.CategoriaReceitaORM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {

    public List<CategoriaDTO> toCategoriaReceitaDTO(List<CategoriaReceitaORM> listCategoriaReceitaORM) {
        List<CategoriaDTO> listCategoriaDTO = new ArrayList<>();

        for(CategoriaReceitaORM categoriaORM: listCategoriaReceitaORM) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();

            categoriaDTO.setId(categoriaORM.getId());
            categoriaDTO.setDescricao(categoriaORM.getDescricao());

            listCategoriaDTO.add(categoriaDTO);
        }

        return listCategoriaDTO;
    }

    public List<CategoriaDTO> toCategoriaDespesaDTO(List<CategoriaDespesaORM> listCategoriaDespesaORM) {
        List<CategoriaDTO> listCategoriaDTO = new ArrayList<>();

        for(CategoriaDespesaORM categoriaORM: listCategoriaDespesaORM) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();

            categoriaDTO.setId(categoriaORM.getId());
            categoriaDTO.setDescricao(categoriaORM.getDescricao());

            listCategoriaDTO.add(categoriaDTO);
        }

        return listCategoriaDTO;
    }
}
