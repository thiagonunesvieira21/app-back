package br.com.aluguel.json.bean.cadastral;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by thiago on 29/06/17.
 */
@ApiModel(value = "produto")
public class AlterarProduto {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String nome;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String descricao;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 500)
    private String palavrasChave;

    @NotNull
    private BigDecimal medida;

    @ApiModelProperty(required=true)
    @NotNull
    private Integer categoriaId, marcaFabricanteId, unidadeMedidaId, destaque;

    public AlterarProduto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public BigDecimal getMedida() {
        return medida;
    }

    public void setMedida(BigDecimal medida) {
        this.medida = medida;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getMarcaFabricanteId() {
        return marcaFabricanteId;
    }

    public void setMarcaFabricanteId(Integer marcaFabricanteId) {
        this.marcaFabricanteId = marcaFabricanteId;
    }

    public Integer getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(Integer unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public Integer getDestaque() {
        return destaque;
    }

    public void setDestaque(Integer destaque) {
        this.destaque = destaque;
    }
}
