package br.com.aluguel.entity.cadastral;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.aluguel.entity.suporte.AcessoUsuario;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiago on 29/12/15.
 */
@Entity
@XmlRootElement
@Table(name = "lista_compra")
@SequenceGenerator(name = "lista_compra_seq", sequenceName = "cadastral.seq_nu_lista_compra", allocationSize = 1, initialValue = 1)
public class ListaCompra implements Serializable{

    private Integer id;
    private String nome;
    private AcessoUsuario cliente;
//    private List<ItemListaCompra> itemsListaCompra;

    @Id
    @Column(name = "nu_lista_compra")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lista_compra_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "no_lista_compra")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nu_usuario", referencedColumnName = "nu_usuario")
    public AcessoUsuario getCliente() {
        return cliente;
    }

    public void setCliente(AcessoUsuario cliente) {
        this.cliente = cliente;
    }

//    @OneToMany(mappedBy = "listaCompra", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    public List<ItemListaCompra> getItemsListaCompra() {
//        return itemsListaCompra;
//    }
//
//    public void setItemsListaCompra(List<ItemListaCompra> itemsListaCompra) {
//        this.itemsListaCompra = itemsListaCompra;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListaCompra that = (ListaCompra) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}