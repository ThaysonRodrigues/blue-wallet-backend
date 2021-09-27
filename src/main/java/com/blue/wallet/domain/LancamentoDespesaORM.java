package com.blue.wallet.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_LANCAMENTO_DESPESA", schema = SchemaConstant.DEFAULT)
public class LancamentoDespesaORM {

    /*************************************************
     ** COLUMNS
     *************************************************/

    @Id
    @SequenceGenerator(name = "sqLancamentoDespesa", sequenceName = SchemaConstant.DEFAULT + ".SQ_TB_LANCAMENTO_DESPESA", allocationSize = 1)
    @GeneratedValue(generator = "sqLancamentoDespesa", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "NUMERO_PARCELAS")
    private int numeroParcelas;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Column(name = "FLG_PAGAMENTO_EFETUADO")
    private boolean flgPagamentoEfetuado;

    /*************************************************
     ** RELATIONSHIPS
     *************************************************/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    private UsuarioORM usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIA_DESPESA_ID", referencedColumnName = "ID")
    private CategoriaDespesaORM categoriaDespesa;

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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public CategoriaDespesaORM getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(CategoriaDespesaORM categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }
}
