package br.com.json.bean.cadastral;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by thiago on 29/06/17.
 */
@ApiModel(value = "atributo")
public class AtributoProduto {

    private Integer id;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String nome;


    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 100)
    private String texto;

    @NotNull
    private Integer ordem;

    public AtributoProduto() {
    }

    public AtributoProduto(String nome, String texto, Integer ordem) {
        this.nome = nome;
        this.texto = texto;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
