package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoReceitaORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceitaORM, Integer> {

    @Query("select l from LancamentoReceitaORM l inner join l.usuario u where u.id = :idUsuario and to_char(l.dataLancamento, 'YYYY-MM') = :data")
    Page<LancamentoReceitaORM> findLancamentoPorData(@Param("idUsuario") Integer idIsuario, @Param("data") String data,
                                                     Pageable pageable);
}
