package com.blue.wallet.domain;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_USUARIO", schema = SchemaConstant.DEFAULT)
public class UsuarioORM {

    /*************************************************
     ** COLUMNS
     *************************************************/

    @Id
    @SequenceGenerator(name = "sqUsuario", sequenceName = SchemaConstant.DEFAULT + ".SQ_TB_USUARIO", allocationSize = 1)
    @GeneratedValue(generator = "sqUsuario", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    @Column(name = "GOOGLE_CODE")
    private String googleCode;

    @Column(name = "CODIGO_ALTERACAO_SENHA")
    private Integer codigoAlteracaoSenha;

    @Column(name = "DATA_SOLICITACAO_ALTERAR_SENHA")
    private LocalDate dataSolicitacaoAlterarSenha;

    /*************************************************
     ** RELATIONSHIPS
     *************************************************/

    /*************************************************
     ** CONSTRUCTORS
     *************************************************/

    public UsuarioORM() {}

    public UsuarioORM(Integer id) {
        this.id = id;
    }

    /*************************************************
     ** AUXILIAR METHODS
     *************************************************/

    /*************************************************
     ** EQUALS AND HASHCODE
     *************************************************/

    /*************************************************
     ** GETTERS AND SETTERS
     *************************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGoogleCode() {
        return googleCode;
    }

    public void setGoogleCode(String googleCode) {
        this.googleCode = googleCode;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getCodigoAlteracaoSenha() {
        return codigoAlteracaoSenha;
    }

    public void setCodigoAlteracaoSenha(Integer codigoAlteracaoSenha) {
        this.codigoAlteracaoSenha = codigoAlteracaoSenha;
    }

    public LocalDate getDataSolicitacaoAlterarSenha() {
        return dataSolicitacaoAlterarSenha;
    }

    public void setDataSolicitacaoAlterarSenha(LocalDate dataSolicitacaoAlterarSenha) {
        this.dataSolicitacaoAlterarSenha = dataSolicitacaoAlterarSenha;
    }
}
