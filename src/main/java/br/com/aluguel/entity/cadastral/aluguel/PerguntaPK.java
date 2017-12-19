package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PerguntaPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_pergunta", nullable = false, insertable = true, updatable = true)
	private Date dhPergunta;

    @Column(name = "nu_anuncio", nullable = false, insertable = true, updatable = true)
	private Integer idAnuncio;
	
    @Column(name = "nu_locatario", nullable = false, insertable = true, updatable = true)
	private Integer idLocatario;

    
	public PerguntaPK(Date dhPergunta, Integer idAnuncio, Integer idLocatario) {
		super();
		this.dhPergunta = dhPergunta;
		this.idAnuncio = idAnuncio;
		this.idLocatario = idLocatario;
	}

	public Date getDhPergunta() {
		return dhPergunta;
	}

	public void setDhPergunta(Date dhPergunta) {
		this.dhPergunta = dhPergunta;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dhPergunta == null) ? 0 : dhPergunta.hashCode());
		result = prime * result + ((idAnuncio == null) ? 0 : idAnuncio.hashCode());
		result = prime * result + ((idLocatario == null) ? 0 : idLocatario.hashCode());
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
		PerguntaPK other = (PerguntaPK) obj;
		if (dhPergunta == null) {
			if (other.dhPergunta != null)
				return false;
		} else if (!dhPergunta.equals(other.dhPergunta))
			return false;
		if (idAnuncio == null) {
			if (other.idAnuncio != null)
				return false;
		} else if (!idAnuncio.equals(other.idAnuncio))
			return false;
		if (idLocatario == null) {
			if (other.idLocatario != null)
				return false;
		} else if (!idLocatario.equals(other.idLocatario))
			return false;
		return true;
	}

}
