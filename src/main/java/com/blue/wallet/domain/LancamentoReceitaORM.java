package com.blue.wallet.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_LANCAMENTO_RECEITA", schema = SchemaConstant.DEFAULT)
public class LancamentoReceitaORM {

    /*************************************************
     ** COLUMNS
     *************************************************/

    @Id
    @SequenceGenerator(name = "sqLancamentoReceita", sequenceName = SchemaConstant.DEFAULT + ".SQ_TB_LANCAMENTO_RECEITA", allocationSize = 1)
    @GeneratedValue(generator = "sqLancamentoReceita", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "NUMERO_PARCELAS")
    private int numeroParcelas;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "DATA_LANCAMENTO")
    private LocalDate dataLancamento;

    @Column(name = "FLG_PAGAMENTO_EFETUADO")
    private boolean flgPagamentoEfetuado;

    /*************************************************
     ** RELATIONSHIPS
     *************************************************/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    private UsuarioORM usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIA_RECEITA_ID", referencedColumnName = "ID")
    private CategoriaReceitaORM categoriaReceita;

    /*************************************************
     ** CONSTRUCTORS
     *************************************************/

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

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isFlgPagamentoEfetuado() {
        return flgPagamentoEfetuado;
    }

    public void setFlgPagamentoEfetuado(boolean flgPagamentoEfetuado) {
        this.flgPagamentoEfetuado = flgPagamentoEfetuado;
    }

    public UsuarioORM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioORM usuario) {
        this.usuario = usuario;
    }

    public CategoriaReceitaORM getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(CategoriaReceitaORM categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
    }
}
