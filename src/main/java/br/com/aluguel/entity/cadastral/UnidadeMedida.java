package br.com.aluguel.entity.cadastral;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "unidade_medida", schema = "cadastral")
@SequenceGenerator(name = "unidade_medida_seq", sequenceName = "cadastral.seq_nu_unidade", allocationSize = 1, initialValue = 1)
@ApiModel(value="UnidadeMedida")
public class UnidadeMedida {
    private Integer id;
    private String sigla;
    private String nome;

    @Id
    @Column(name = "nu_unidade_medida")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "unidade_medida_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 5)
    @Column(name = "sg_unidade_medida")
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @NotNull
    @Size(max = 20)
    @Column(name = "no_unidade_medida")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnidadeMedida that = (UnidadeMedida) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
