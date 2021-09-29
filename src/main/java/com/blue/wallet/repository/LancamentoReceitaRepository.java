package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoReceitaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceitaORM, Integer> {

    @Query("select l from LancamentoReceitaORM l inner join l.usuario u where u.id = :idUsuario and to_char(l.dataLancamento, 'YYYY-MM') = :data")
    List<LancamentoReceitaORM> findLancamentoPorData(@Param("idUsuario") Integer idIsuario, @Param("data") String data);
}
