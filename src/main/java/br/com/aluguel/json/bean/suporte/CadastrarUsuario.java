package br.com.aluguel.json.bean.suporte;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@ApiModel(value="Usuario")
public class CadastrarUsuario {
	
	@ApiModelProperty(required=true)
	@NotEmpty
	@NotNull
	private String nome, deLogin;
	 
	@ApiModelProperty(required=true)
	@NotEmpty
	@NotNull
	@Email
	private String email;
	
	@ApiModelProperty(required=true, notes="String sem formatação, apenas números")
	@NotNull
	@CPF
	private String cpf;
	
	@ApiModelProperty(required=true)
	@NotNull
	private String sexo, nroCelular, status;

	@ApiModelProperty(required = false)
	private String rg, rgOrgaoEmissor, nroTelFixo;

	@ApiModelProperty(required = false)
	private Date rgDataEmissao;


	@ApiModelProperty(required=true)
	@NotNull
	private Date dataNascimento;

	public CadastrarUsuario() {}

	public CadastrarUsuario(String nome, String deLogin, String email, String cpf, String sexo, String nroCelular, String status, String rg, String rgOrgaoEmissor, String nroTelFixo, Date rgDataEmissao, Date dataNascimento) {
		this.nome = nome;
		this.deLogin = deLogin;
		this.email = email;
		this.cpf = cpf;
		this.sexo = sexo;
		this.nroCelular = nroCelular;
		this.status = status;
		this.rg = rg;
		this.rgOrgaoEmissor = rgOrgaoEmissor;
		this.nroTelFixo = nroTelFixo;
		this.rgDataEmissao = rgDataEmissao;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDeLogin() {
		return deLogin;
	}

	public void setDeLogin(String deLogin) {
		this.deLogin = deLogin;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getNroTelFixo() {
		return nroTelFixo;
	}

	public void setNroTelFixo(String nroTelFixo) {
		this.nroTelFixo = nroTelFixo;
	}

	public Date getRgDataEmissao() {
		return rgDataEmissao;
	}

	public void setRgDataEmissao(Date rgDataEmissao) {
		this.rgDataEmissao = rgDataEmissao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}