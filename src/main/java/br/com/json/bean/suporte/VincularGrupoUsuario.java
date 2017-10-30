package br.com.json.bean.suporte;

import io.swagger.annotations.ApiModel;

@ApiModel
public class VincularGrupoUsuario {
	
	private Integer idUsuario;
	
	private Integer[] idGrupos;
	
	public VincularGrupoUsuario(){}

	public VincularGrupoUsuario(Integer idUsuario, Integer[] idGrupos) {
		super();
		this.idUsuario = idUsuario;
		this.idGrupos = idGrupos;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer[] getIdGrupos() {
		return idGrupos;
	}

	public void setIdGrupos(Integer[] idGrupos) {
		this.idGrupos = idGrupos;
	}
	
	

}
