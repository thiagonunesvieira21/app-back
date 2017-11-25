package br.com.aluguel.json.bean.cadastral;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by thiago on 30/06/17.
 */
@ApiModel(value = "deposito")
public class CadastrarDeposito {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 8)
    private String codigo;

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 30)
    private String nome;

    private Long cidade;

    @Size(max = 8)
    private String cep;

    @Size(max = 120)
    private String endereco;

    public CadastrarDeposito(Long cidade) {
        this.cidade = cidade;
    }

    public CadastrarDeposito(String codigo, String nome, Long cidade, String cep, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.cep = cep;
        this.endereco = endereco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
