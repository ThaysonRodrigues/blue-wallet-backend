package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoDespesaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesaORM, Integer> {

    @Query("select l from LancamentoDespesaORM l inner join l.usuario u where u.id = :idUsuario and to_char(l.dataPagamento, 'YYYY-MM') = :data")
    List<LancamentoDespesaORM> findLancamentoPorData(@Param("idUsuario") Integer idIsuario, @Param("data") String data);
}
