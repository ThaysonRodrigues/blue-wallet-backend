package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoReceitaORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceitaORM, Integer> {
}
