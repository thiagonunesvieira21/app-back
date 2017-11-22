package br.com.aluguel.entity.cadastral;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "deposito", schema = "cadastral")
@SequenceGenerator(name = "deposito_seq", sequenceName = "seq_nu_deposito", allocationSize = 1, initialValue = 1)
@ApiModel(value="Deposito")
public class Deposito implements Serializable{
    private Integer id;
    private String codigo;
    private String nome;
    private Long cidade;
    private String cep;
    private String endereco;

    @Id
    @Column(name = "nu_deposito")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposito_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "co_deposito")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "no_deposito")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "no_deposito_cidade")
    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    @Column(name = "de_deposito_cep")
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Column(name = "de_endereco_deposito")
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposito deposito = (Deposito) o;
        return Objects.equal(id, deposito.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
