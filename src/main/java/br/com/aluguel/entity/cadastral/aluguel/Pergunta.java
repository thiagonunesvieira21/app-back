package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "pergunta", schema = "aluguel")
@ApiModel(value="Pergunta")
public class Pergunta implements Serializable{
	private static final long serialVersionUID = -4570928296382561618L;

	@EmbeddedId
	private PerguntaPK id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_pergunta", nullable = false, insertable = false, updatable = false)
	private Date dhPergunta;

	@Column(name = "de_pergunta")
	private String dePergunta;

	@Column(name = "de_resposta_pergunta")
	private String deRespostaPergunta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_resposta_pergunta")
	private Date dhRespostaPergunta;

	@Column(name = "vr_nota_resposta")
	private Integer vrNotaResposta;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_anuncio", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_pergunta_anuncio"))
	private Anuncio anuncio;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_locatario", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_pergunta_locatario"))
	private Locatario locatario;
	
	public PerguntaPK getId() {
		return id;
	}

	public void setId(PerguntaPK id) {
		this.id = id;
	}

	public Date getDhPergunta() {
		return dhPergunta;
	}

	public void setDhPergunta(Date dhPergunta) {
		this.dhPergunta = dhPergunta;
	}

	public String getDePergunta() {
		return dePergunta;
	}

	public void setDePergunta(String dePergunta) {
		this.dePergunta = dePergunta;
	}

	public String getDeRespostaPergunta() {
		return deRespostaPergunta;
	}

	public void setDeRespostaPergunta(String deRespostaPergunta) {
		this.deRespostaPergunta = deRespostaPergunta;
	}

	public Date getDhRespostaPergunta() {
		return dhRespostaPergunta;
	}

	public void setDhRespostaPergunta(Date dhRespostaPergunta) {
		this.dhRespostaPergunta = dhRespostaPergunta;
	}

	public Integer getVrNotaResposta() {
		return vrNotaResposta;
	}

	public void setVrNotaResposta(Integer vrNotaResposta) {
		this.vrNotaResposta = vrNotaResposta;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
