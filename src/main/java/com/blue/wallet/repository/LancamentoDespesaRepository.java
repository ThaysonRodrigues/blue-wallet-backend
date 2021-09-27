package com.blue.wallet.repository;

import com.blue.wallet.domain.LancamentoDespesaORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesaORM, Integer> {
}
