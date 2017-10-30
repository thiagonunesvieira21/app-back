package br.com.json.bean.cadastral;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
}
