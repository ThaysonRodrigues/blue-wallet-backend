package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoDespesaORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesaORM, Integer> {

    @Query("select l from LancamentoDespesaORM l inner join l.usuario u where u.id = :idUsuario and to_char(l.dataPagamento, 'YYYY-MM') = :data")
    Page<LancamentoDespesaORM> findLancamentoPorData(@Param("idUsuario") Integer idIsuario, @Param("data") String data, Pageable pageable);
}
