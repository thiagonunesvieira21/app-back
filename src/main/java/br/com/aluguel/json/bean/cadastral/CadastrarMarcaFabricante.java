package br.com.aluguel.json.bean.cadastral;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by thiago on 21/06/17.
 */
@ApiModel(value = "marca")
public class CadastrarMarcaFabricante {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    private String nome;

    @ApiModelProperty(required=false)
    private String descricao;

    public CadastrarMarcaFabricante() {
    }

    public CadastrarMarcaFabricante(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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
}
