package br.com.aluguel.entity.cadastral;

import com.google.common.base.Objects;
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
@Table(name = "imagem", schema = "cadastral")
@SequenceGenerator(name = "image_seq", sequenceName = "cadastral.seq_nu_imagem", allocationSize = 1, initialValue = 1)
@ApiModel(value="Imagem")
public class Imagem {
    private Integer id;
    private String caminho;
    private String titulo;
    private Integer ordem;
    private Produto produto;
    private Integer idProduto;


    public Imagem() {
        this.ordem = 0;
    }

    @Id
    @Column(name = "nu_imagem")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 200)
    @Column(name = "de_imagem_caminho")
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    @NotNull
    @Size(max = 30)
    @Column(name = "de_imagem_titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotNull
    @Column(name = "nu_imagem_ordem")
    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @ManyToOne
    @NotNull
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
        Imagem imagem = (Imagem) o;
        return id == imagem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
