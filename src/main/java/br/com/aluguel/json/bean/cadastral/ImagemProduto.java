package br.com.aluguel.json.bean.cadastral;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by thiago on 29/06/17.
 */
@ApiModel(value = "imagem")
public class ImagemProduto {

    private Integer id;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 30)
    private String titulo;


    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String caminho;

    @NotNull
    private Integer ordem;

    public ImagemProduto() {
    }

    public ImagemProduto(String titulo, String caminho, Integer ordem) {
        this.titulo = titulo;
        this.caminho = caminho;
        this.ordem = ordem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
