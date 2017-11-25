package br.com.aluguel.entity.cadastral;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

import io.swagger.annotations.ApiModel;

/**
 * Created by thiago on 20/11/15.
 */

@Entity
@Table(name = "atributo", schema = "cadastral")
@SequenceGenerator(name = "atributo_seq", sequenceName = "cadastral.seq_nu_atributo_atributo", allocationSize = 1, initialValue = 1)
@ApiModel(value="Atributo")
public class Atributo implements Serializable {
    private Integer id;
    private String nome;
    private String texto;
    private Produto produto;
    private Integer idProduto;
    private Integer ordem;

    public Atributo() {
        this.ordem = 0;
    }

    @Id
    @Column(name = "nu_atributo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atributo_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 20)
    @Column(name = "no_atributo")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "de_atributo")
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @NotNull
    @Column(name = "nu_atributo_ordem")
    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nu_produto", referencedColumnName = "nu_produto", insertable = true, updatable = true)
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Column(name = "nu_produto", insertable = false, updatable = false)
    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atributo atributo = (Atributo) o;
        return id == atributo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
