package com.blue.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.wallet.domain.UsuarioORM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioORM, Integer> {

    Optional<UsuarioORM> findByEmail(String email);

    @Query("SELECT u.nome FROM UsuarioORM u where u.id = :idUsuario")
    String getUsername(@Param("idUsuario") Integer idIsuario);
}
