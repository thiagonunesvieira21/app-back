package br.com.aluguel.json.bean.suporte;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "grupo")
public class CadastrarGrupo {

	public CadastrarGrupo() {}
	
	public CadastrarGrupo(Integer idNivelHierarquia, Integer idGrupoPai, Integer idProcessadora, Integer idInstituicao,
			Integer idRegional, Integer idFilial, Integer idPontoDeRelacionamento, String descGrupo) {
		this.idNivelHierarquia = idNivelHierarquia;
		this.idGrupoPai = idGrupoPai;
		this.idProcessadora = idProcessadora;
		this.idInstituicao = idInstituicao;
		this.idRegional = idRegional;
		this.idFilial = idFilial;
		this.idPontoDeRelacionamento = idPontoDeRelacionamento;
		this.descGrupo = descGrupo;
	}



	@NotNull
	@ApiModelProperty(required=true)
	private Integer idNivelHierarquia, idGrupoPai, idProcessadora;
	private Integer idInstituicao, idRegional, idFilial, idPontoDeRelacionamento;
	
	@NotNull
	@NotEmpty
	private String descGrupo;

	public Integer getIdNivelHierarquia() {
		return idNivelHierarquia;
	}

	public void setIdNivelHierarquia(Integer idNivelHierarquia) {
		this.idNivelHierarquia = idNivelHierarquia;
	}

	public Integer getIdGrupoPai() {
		return idGrupoPai;
	}

	public void setIdGrupoPai(Integer idGrupoPai) {
		this.idGrupoPai = idGrupoPai;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdRegional() {
		return idRegional;
	}

	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}

	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}

	public Integer getIdPontoDeRelacionamento() {
		return idPontoDeRelacionamento;
	}

	public void setIdPontoDeRelacionamento(Integer idPontoDeRelacionamento) {
		this.idPontoDeRelacionamento = idPontoDeRelacionamento;
	}

	public String getDescGrupo() {
		return descGrupo;
	}

	public void setDescGrupo(String descGrupo) {
		this.descGrupo = descGrupo;
	}

	public Integer getIdProcessadora() {
		return idProcessadora;
	}

	public void setIdProcessadora(Integer idProcessadora) {
		this.idProcessadora = idProcessadora;
	}
	
}