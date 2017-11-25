package br.com.aluguel.entity.cadastral;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "categoria", schema = "cadastral")
@SequenceGenerator(name = "categoria_seq", sequenceName = "cadastral.seq_nu_categoria", allocationSize = 1, initialValue = 1)
@ApiModel(value="Categoria")
public class Categoria implements Serializable{
    private Integer id;
    private String nome;
    private String tipo;
    private List<Produto> produtos;

    public Categoria() {
    }

    @Id
    @Column(name = "nu_categoria")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "categoria_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 30)
    @Column(name = "no_categoria")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @NotNull
    @Column(name = "ic_tipo_categoria")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (id != categoria.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
