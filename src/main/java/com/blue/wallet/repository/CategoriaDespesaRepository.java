package com.blue.wallet.repository;

import com.blue.wallet.domain.CategoriaDespesaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaDespesaRepository extends JpaRepository<CategoriaDespesaORM, Integer> {

    @Query("SELECT c FROM CategoriaDespesaORM c WHERE c.flg_ativo = true")
    List<CategoriaDespesaORM> findCategoriaDespesa();

}
