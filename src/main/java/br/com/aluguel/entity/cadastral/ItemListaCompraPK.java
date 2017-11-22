package br.com.aluguel.entity.cadastral;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;

/**
 * Created by thiago on 29/12/15.
 */
public class ItemListaCompraPK implements Serializable {
    private Integer nuProduto;
    private Integer nuListaCompra;

    @Id
    @Column(name = "nu_produto", nullable = false, insertable = true, updatable = true)
    public Integer getNuProduto() {
        return nuProduto;
    }

    public void setNuProduto(Integer nuProduto) {
        this.nuProduto = nuProduto;
    }
    
    @Id
    @Column(name = "nu_lista_compra", nullable = false, insertable = true, updatable = true)
    public Integer getNuListaCompra() {
        return nuListaCompra;
    }

    public void setNuListaCompra(Integer nuListaCompra) {
        this.nuListaCompra = nuListaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemListaCompraPK that = (ItemListaCompraPK) o;

        if (nuListaCompra != null ? !nuListaCompra.equals(that.nuListaCompra) : that.nuListaCompra != null)
            return false;
        if (nuProduto != null ? !nuProduto.equals(that.nuProduto) : that.nuProduto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nuProduto != null ? nuProduto.hashCode() : 0;
        result = 31 * result + (nuListaCompra != null ? nuListaCompra.hashCode() : 0);
        return result;
    }
}
