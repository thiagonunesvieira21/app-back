package br.com.entity.cadastral;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@IdClass(EstoquePK.class)
@Table(name = "estoque", schema = "cadastral")
@ApiModel(value="Produto")
public class Estoque implements Serializable{
    private BigDecimal quantidade;
    private Integer produtoId;
    private Integer depositoId;
    private Deposito deposito;
    private Produto produto;
    private String coLote;
    private Date dtVencimento;

	@NotNull
    @Digits(integer = 9, fraction = 3)
    @Column(name = "qt_estoque")
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    @Id
    @Column(name = "nu_deposito", insertable = false, updatable = false)
    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    @Id
    @Column(name = "nu_produto", insertable = true, updatable = true)
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Column(name = "co_lote_produto")
    public String getCoLote() {
        return coLote;
    }

    public void setCoLote(String coLote) {
        this.coLote = coLote;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_vencimento_produto")
    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "nu_deposito", referencedColumnName = "nu_deposito", insertable = false, updatable = false)
    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "nu_produto", referencedColumnName = "nu_produto", insertable = false, updatable = false)
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equal(produtoId, estoque.produtoId) &&
                Objects.equal(depositoId, estoque.depositoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(produtoId, depositoId);
    }
}
