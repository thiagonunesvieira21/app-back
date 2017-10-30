package br.com.json.bean.cadastral;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by thiago on 28/06/17.
 */
@ApiModel(value = "unidadeMedida")
public class CadastrarUnidadeMedida {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 5)
    private String sigla;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String nome;

    public CadastrarUnidadeMedida() {
    }

    public CadastrarUnidadeMedida(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
