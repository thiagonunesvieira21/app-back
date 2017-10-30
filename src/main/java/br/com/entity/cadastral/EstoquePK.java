package br.com.entity.cadastral;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by thiago on 20/11/15.
 */
public class EstoquePK implements Serializable {
    private Integer depositoId;
    private Integer produtoId;

    @Id
    @Column(name = "nu_deposito", nullable = false, insertable = true, updatable = true)
    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    @Id
    @Column(name = "nu_produto", nullable = false, insertable = true, updatable = true)
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public EstoquePK(Integer depositoId, Integer produtoId) {
        this.depositoId = depositoId;
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoquePK estoquePK = (EstoquePK) o;
        return Objects.equal(depositoId, estoquePK.depositoId) &&
                Objects.equal(produtoId, estoquePK.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(depositoId, produtoId);
    }
}
