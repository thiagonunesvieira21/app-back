package br.com.aluguel.entity.cadastral;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by thiago on 29/12/15.
 */
@Entity
@Table(name = "item_lista_compra")
@IdClass(ItemListaCompraPK.class)
public class ItemListaCompra {

    private BigDecimal quantidade;
    private Integer nuProduto;
    private Integer nuListaCompra;
//    private Produto produto;
//    private ListaCompra listaCompra;

    @Id
    @NotNull
    @Column(name = "nu_lista_compra", insertable = true, updatable = true)
    public Integer getNuListaCompra() {
        return nuListaCompra;
    }

    public void setNuListaCompra(Integer nuListaCompra) {
        this.nuListaCompra = nuListaCompra;
    }

    @Id
    @NotNull
    @Column(name = "nu_produto", insertable = true, updatable = true)
    public Integer getNuProduto() {
        return nuProduto;
    }

    public void setNuProduto(Integer nuProduto) {
        this.nuProduto = nuProduto;
    }

    @NotNull
    @Column(name = "qt_produto_lista_compra", insertable = true, updatable = true)
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

//    @ManyToOne
//    @JoinColumn(name = "nu_lista_compra", referencedColumnName = "nu_lista_compra", insertable = false, updatable = false)
//    public ListaCompra getListaCompra() {
//        return listaCompra;
//    }
//
//    public void setListaCompra(ListaCompra listaCompra) {
//        this.listaCompra = listaCompra;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "nu_produto", referencedColumnName = "nu_produto", insertable = false, updatable = false)
//    public Produto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(Produto produto) {
//        this.produto = produto;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemListaCompra that = (ItemListaCompra) o;

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
