package com.blue.wallet.repository;

import com.blue.wallet.domain.CategoriaReceitaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaReceitaRepository extends JpaRepository<CategoriaReceitaORM, Integer> {

    @Query("SELECT c FROM CategoriaReceitaORM c WHERE c.flg_ativo = true")
    List<CategoriaReceitaORM> findCategoriaReceita();
}
