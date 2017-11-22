package br.com.aluguel.json.bean.suporte;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel
public class UpdateUsuario {

    public UpdateUsuario() {
    }

    public UpdateUsuario(String nome, String login, String email, String cpf, Integer sexo,
                         Integer dddNroFixo, Integer nroFixo, Integer dddNroCelular, Integer nroCelular,
                         Integer status, String rg, String rgOrgaoEmissor, LocalDateTime rgDataEmissao,
                         LocalDateTime dataNascimento) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dddNroFixo = dddNroFixo;
        this.nroFixo = nroFixo;
        this.dddNroCelular = dddNroCelular;
        this.nroCelular = nroCelular;
        this.status = status;
        this.rg = rg;
        this.rgOrgaoEmissor = rgOrgaoEmissor;
        this.rgDataEmissao = rgDataEmissao;
        this.dataNascimento = dataNascimento;
    }

    @ApiModelProperty(required = true)
    @NotEmpty
    @NotNull
    private String nome, login;

    @ApiModelProperty(required = true)
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @ApiModelProperty(required = true, notes = "String sem formatação, apenas números")
    @NotNull
    private String cpf;

    @ApiModelProperty(required = true)
    @NotNull
    private Integer sexo, dddNroFixo, nroFixo, dddNroCelular, nroCelular, status;

    @ApiModelProperty(required = true)
    @NotEmpty
    @NotNull
    private String rg, rgOrgaoEmissor;

    @ApiModelProperty(required = true)
    @NotNull
    private LocalDateTime rgDataEmissao, dataNascimento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Integer getDddNroFixo() {
        return dddNroFixo;
    }

    public void setDddNroFixo(Integer dddNroFixo) {
        this.dddNroFixo = dddNroFixo;
    }

    public Integer getNroFixo() {
        return nroFixo;
    }

    public void setNroFixo(Integer nroFixo) {
        this.nroFixo = nroFixo;
    }

    public Integer getDddNroCelular() {
        return dddNroCelular;
    }

    public void setDddNroCelular(Integer dddNroCelular) {
        this.dddNroCelular = dddNroCelular;
    }

    public Integer getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(Integer nroCelular) {
        this.nroCelular = nroCelular;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoEmissor() {
        return rgOrgaoEmissor;
    }

    public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
        this.rgOrgaoEmissor = rgOrgaoEmissor;
    }

    public LocalDateTime getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(LocalDateTime rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
