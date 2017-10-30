package br.com.entity.cadastral;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.Set;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "produto", schema = "cadastral")
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_produto_id_seq", allocationSize = 1, initialValue = 1)
@ApiModel(value="Produto")
public class Produto implements Serializable{
    private Integer id;
    private String nome;
    private BigDecimal medida;
    private String descricao;
    private String palavrasChave;
    private String codigo;
    private Integer destaque;
    private Set<Atributo> atributos;
    private Set<Estoque> estoques;
    private Set<Imagem> imagems;
    private Categoria categoria;
    private MarcaFabricante marcaFabricante;
    private UnidadeMedida unidadeMedida;
    private Integer categoriaId;
    private Integer marcaFabricanteId;
    private Integer unidadeMedidaId;
    private Set<RelacaoProduto> relacaoProdutos;

    @Id
    @Column(name = "nu_produto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 50)
    @Column(name = "no_produto_nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    @Column(name = "qt_produto_medida")
    public BigDecimal getMedida() {
        return medida;
    }

    public void setMedida(BigDecimal medida) {
        this.medida = medida;
    }

    @NotNull
    @Size(max = 200)
    @Column(name = "de_produto")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    @Size(max = 10)
    @Column(name = "co_produto")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @NotNull
    @Size(max = 500)
    @Column(name = "de_palavras_chave")
    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    @NotNull
    @Column(name = "ic_produto_destaque")
    public Integer getDestaque() {
        return destaque;
    }

    public void setDestaque(Integer destaque) {
        this.destaque = destaque;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(Set<Atributo> atributos) {
        this.atributos = atributos;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    public Set<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(Set<Estoque> estoques) {
        this.estoques = estoques;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(Set<Imagem> imagems) {
        this.imagems = imagems;
    }


    @ManyToOne
    @JoinColumn(name = "nu_categoria", referencedColumnName = "nu_categoria", insertable = false, updatable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @ManyToOne
    @JoinColumn(name = "nu_marca_fabricante", referencedColumnName = "nu_marca_fabricante", insertable = false, updatable = false)
    public MarcaFabricante getMarcaFabricante() {
        return marcaFabricante;
    }

    public void setMarcaFabricante(MarcaFabricante marcaFabricante) {
        this.marcaFabricante = marcaFabricante;
    }

    @ManyToOne
    @JoinColumn(name = "nu_unidade_medida", referencedColumnName = "nu_unidade_medida", insertable = false, updatable = false)
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @NotNull
    @Column(name = "nu_categoria", insertable = true, updatable = true)
    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    @NotNull
    @Column(name = "nu_marca_fabricante", insertable = true, updatable = true)
    public Integer getMarcaFabricanteId() {
        return marcaFabricanteId;
    }

    public void setMarcaFabricanteId(Integer marcaFabricanteId) {
        this.marcaFabricanteId = marcaFabricanteId;
    }

    @NotNull
    @Column(name = "nu_unidade_medida", insertable = true, updatable = true)
    public Integer getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(Integer unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    public Set<RelacaoProduto> getRelacaoProdutos() {
        return relacaoProdutos;
    }

    public void setRelacaoProdutos(Set<RelacaoProduto> relacaoProdutos) {
        this.relacaoProdutos = relacaoProdutos;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (!id.equals(produto.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
