package br.com.aluguel.entity.cadastral;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by thiago on 11/07/17.
 */
public class PrecoProdutoPK implements Serializable {
    private Integer produtoId;
    private Date dtInicio;
    private Date dtFim;

    @Column(name = "nu_produto", nullable = false, insertable = false, updatable = false)
    @Id
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_inicio_preco", nullable = false, insertable = false, updatable = false)
    @Id
    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_fim_preco", nullable = false, insertable = false, updatable = false)
    @Id
    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        PrecoProdutoPK that = (PrecoProdutoPK) o;
        return Objects.equal(produtoId, that.produtoId) &&
                Objects.equal(dtInicio, that.dtInicio) &&
                Objects.equal(dtFim, that.dtFim);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(produtoId, dtInicio, dtFim);
    }
}
