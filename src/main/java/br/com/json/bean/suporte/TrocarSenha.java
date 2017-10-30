package br.com.json.bean.suporte;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class TrocarSenha {
	
	@ApiModelProperty(required=true)
	@JsonProperty(required=true)
	private String login, senha, novaSenha;
	
	

	public TrocarSenha() {
	}

	public TrocarSenha(String login, String senha, String novaSenha) {
		super();
		this.login = login;
		this.senha = senha;
		this.novaSenha = novaSenha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
