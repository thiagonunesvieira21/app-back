package br.com.aluguel.json.bean.cadastral;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by thiago on 29/06/17.
 */
@ApiModel(value = "anuncio")
public class CadastroAnuncio {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max = 255)
    private String deAnuncio;

    @ApiModelProperty(required=true)
    @NotNull
    private BigDecimal vrUnitario;

    private Set<AtributoAnuncio> atributos;

    private Set<ImagemAnuncio> imagems;

    @ApiModelProperty(required=true)
    @NotNull
    private Integer qtDisponivel, idLocador, idSituacao, idCategoria;

    public CadastroAnuncio() {
    }

	public String getDeAnuncio() {
		return deAnuncio;
	}

	public void setDeAnuncio(String deAnuncio) {
		this.deAnuncio = deAnuncio;
	}

	public BigDecimal getVrUnitario() {
		return vrUnitario;
	}

	public void setVrUnitario(BigDecimal vrUnitario) {
		this.vrUnitario = vrUnitario;
	}

	public Set<AtributoAnuncio> getAtributos() {
		return atributos;
	}

	public void setAtributos(Set<AtributoAnuncio> atributos) {
		this.atributos = atributos;
	}

	public Set<ImagemAnuncio> getImagems() {
		return imagems;
	}

	public void setImagems(Set<ImagemAnuncio> imagems) {
		this.imagems = imagems;
	}

	public Integer getQtDisponivel() {
		return qtDisponivel;
	}

	public void setQtDisponivel(Integer qtDisponivel) {
		this.qtDisponivel = qtDisponivel;
	}

	public Integer getIdLocador() {
		return idLocador;
	}

	public void setIdLocador(Integer idLocador) {
		this.idLocador = idLocador;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
    
}
