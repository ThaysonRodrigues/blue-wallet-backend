package com.blue.wallet.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_CATEGORIA_RECEITA", schema = SchemaConstant.DEFAULT)
public class CategoriaReceitaORM {

    /*************************************************
     ** COLUMNS
     *************************************************/

    @Id
    @SequenceGenerator(name = "sqCategoriaReceita", sequenceName = SchemaConstant.DEFAULT + ".SQ_TB_CATEGORIA_RECEITA", allocationSize = 1)
    @GeneratedValue(generator = "sqCategoriaReceita", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "FLG_ATIVO")
    private boolean flg_ativo;

    /*************************************************
     ** RELATIONSHIPS
     *************************************************/

    /*************************************************
     ** CONSTRUCTORS
     *************************************************/

    public CategoriaReceitaORM() {}

    public CategoriaReceitaORM(Integer id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(boolean flg_ativo) {
        this.flg_ativo = flg_ativo;
    }
}
