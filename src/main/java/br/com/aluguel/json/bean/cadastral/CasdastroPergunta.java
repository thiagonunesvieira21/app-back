package br.com.aluguel.json.bean.cadastral;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "pergunta")
public class CasdastroPergunta {

    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
	private Integer idAnuncio, idLocatario;
    
    @ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max=500)
	private String dePergunta;

    
	public Integer getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Integer idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public Integer getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(Integer idLocatario) {
		this.idLocatario = idLocatario;
	}

	public String getDePergunta() {
		return dePergunta;
	}

	public void setDePergunta(String dePergunta) {
		this.dePergunta = dePergunta;
	}

}
