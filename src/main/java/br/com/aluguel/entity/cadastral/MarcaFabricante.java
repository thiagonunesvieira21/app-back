package br.com.aluguel.entity.cadastral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "marca_fabricante", schema = "cadastral")
@SequenceGenerator(name = "marca_fabricante_seq", sequenceName = "cadastral.seq_nu_marca_fabricante", allocationSize = 1, initialValue = 1)
public class MarcaFabricante {
    private Integer id;
    private String nome;
    private String descricao;

    @Id
    @Column(name = "nu_marca_fabricante")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_fabricante_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 30)
    @Column(name = "no_marca_fabricante")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Size(max = 80)
    @Column(name = "de_marca_fabricante")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarcaFabricante that = (MarcaFabricante) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
