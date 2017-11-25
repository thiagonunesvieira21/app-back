package br.com.aluguel.entity.cadastral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@Table(name = "relacao_produto", schema = "cadastral")
@IdClass(RelacaoProdutoPK.class)
@ApiModel(value="RelacaoProduto")
public class RelacaoProduto {
    private Integer produtoIdRelacao;
    private Integer produtoId;
    private Produto produto;
    private Produto produtoRelacao;

    public RelacaoProduto() {
    }

    public RelacaoProduto(Integer produtoId, Integer produtoIdRelacao) {
        this.produtoId = produtoId;
        this.produtoIdRelacao = produtoIdRelacao;
    }

    @Id
    @Column(name = "nu_produto_relacao", nullable = false, insertable = true, updatable = true)
    public Integer getProdutoIdRelacao() {
        return produtoIdRelacao;
    }

    public void setProdutoIdRelacao(Integer produtoIdRelacao) {
        this.produtoIdRelacao = produtoIdRelacao;
    }

    @Id
    @Column(name = "nu_produto", nullable = false, insertable = true, updatable = true)
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @ManyToOne
    @JoinColumn(name = "nu_produto", referencedColumnName = "nu_produto", insertable = false, updatable = false)
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @ManyToOne
    @JoinColumn(name = "nu_produto_relacao", referencedColumnName = "nu_produto", insertable = false, updatable = false)
    public Produto getProdutoRelacao() {
        return produtoRelacao;
    }

    public void setProdutoRelacao(Produto produtoRelacao) {
        this.produtoRelacao = produtoRelacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelacaoProduto that = (RelacaoProduto) o;

        if (produtoId != null ? !produtoId.equals(that.produtoId) : that.produtoId != null) return false;
        if (produtoIdRelacao != null ? !produtoIdRelacao.equals(that.produtoIdRelacao) : that.produtoIdRelacao != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = produtoIdRelacao != null ? produtoIdRelacao.hashCode() : 0;
        result = 31 * result + (produtoId != null ? produtoId.hashCode() : 0);
        return result;
    }
}
