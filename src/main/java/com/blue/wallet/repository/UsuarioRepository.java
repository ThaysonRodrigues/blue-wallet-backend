package com.blue.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.wallet.domain.UsuarioORM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioORM, Integer> {

    Optional<UsuarioORM> findByEmail(String email);

    Optional<UsuarioORM> findByEmailAndCodigoAlteracaoSenha(String email, Integer codigo);

    @Query("SELECT u FROM UsuarioORM u where u.id = :idUsuario")
    UsuarioORM getUser(@Param("idUsuario") Integer idIsuario);

    @Modifying
    @Query("UPDATE UsuarioORM u set u.nome = :nome, u.celular = :celular, u.dataNascimento = :dataNascimento where u.id = :idUsuario")
    void atualizarDadosCadastrais(@Param("nome") String nome, @Param("celular") String celular, @Param("dataNascimento")
            LocalDate dataNascimento, @Param("idUsuario") Integer idUsuario);
}
