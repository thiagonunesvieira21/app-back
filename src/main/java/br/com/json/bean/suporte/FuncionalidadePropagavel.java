package br.com.json.bean.suporte;

import io.swagger.annotations.ApiModel;

@ApiModel
public class FuncionalidadePropagavel {
	
	public FuncionalidadePropagavel() {
	}
	
	public FuncionalidadePropagavel(Integer idFuncionalidade, Boolean propagavel) {
		this.propagavel = propagavel;
		this.idFuncionalidade = idFuncionalidade;
	}
	
	private Integer idFuncionalidade;
	
	private Boolean propagavel;

	public Integer getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(Integer idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public Boolean getPropagavel() {
		return propagavel;
	}

	public void setPropagavel(Boolean propagavel) {
		this.propagavel = propagavel;
	}
}
