package br.com.aluguel.json.bean.cadastral;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "resposta")
public class RespostaPergunta {
    
	@ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
    @Size(max=500)
	private String deRespostaPergunta;

	@ApiModelProperty(required=true)
    @NotEmpty
    @NotNull
	private Integer vrNotaResposta;

	public String getDeRespostaPergunta() {
		return deRespostaPergunta;
	}

	public void setDeRespostaPergunta(String deRespostaPergunta) {
		this.deRespostaPergunta = deRespostaPergunta;
	}

	public Integer getVrNotaResposta() {
		return vrNotaResposta;
	}

	public void setVrNotaResposta(Integer vrNotaResposta) {
		this.vrNotaResposta = vrNotaResposta;
	}
	
}
