package com.blue.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.wallet.domain.UsuarioORM;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioORM, Integer> {

    Optional<UsuarioORM> findByEmail(String email);
}
