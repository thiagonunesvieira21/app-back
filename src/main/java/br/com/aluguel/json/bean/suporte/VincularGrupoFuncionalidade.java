package br.com.aluguel.json.bean.suporte;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel
public class VincularGrupoFuncionalidade {
	
	private List<FuncionalidadePropagavel> funcionalidades;
	
	private Integer idGrupo;
	
	public VincularGrupoFuncionalidade() {
	}
	
	public VincularGrupoFuncionalidade(List<FuncionalidadePropagavel> funcionalidades,Integer idGrupo) {
		this.funcionalidades = funcionalidades;
		this.idGrupo = idGrupo;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public List<FuncionalidadePropagavel> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<FuncionalidadePropagavel> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

}
