package br.com.aluguel.entity.cadastral;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * Created by thiago on 11/07/17.
 */
@Entity
@Table(name = "preco_produto", schema = "cadastral")
@IdClass(PrecoProdutoPK.class)
@ApiModel(value="PrecoProduto")
public class PrecoProduto {
    private Integer produtoId;
    private Produto produto;
    private Date dtInicio;
    private Date dtFim;

    @Id
    @Column(name = "nu_produto", nullable = true, insertable = true, updatable = true)
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "nu_produto", referencedColumnName = "nu_produto", insertable = true, updatable = true)
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicio_preco", nullable = true, insertable = true, updatable = true)
    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_fim_preco", nullable = true, insertable = true, updatable = true)
    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

}
