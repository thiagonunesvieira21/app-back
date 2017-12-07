package br.com.aluguel.json.bean.cadastral;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by thiago on 29/06/17.
 */
@ApiModel(value = "categoria")
public class CadastrarCategoria {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 30)
    private String nome;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 1)
    private String tipo;
    
    @ApiModelProperty
    private Integer idCategoriaPai;

    public CadastrarCategoria() {
    }

    public CadastrarCategoria(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public Integer getIdCategoriaPai() {
		return idCategoriaPai;
	}

	public void setIdCategoriaPai(Integer idCategoriaPai) {
		this.idCategoriaPai = idCategoriaPai;
	}
    
}
