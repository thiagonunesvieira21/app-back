package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AvaliacaoPK implements Serializable {
	private static final long serialVersionUID = 5969511416310429283L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_avaliacao", nullable = false, insertable = true, updatable = true)
	private Date dhAvaliacao;
	
    @Column(name = "nu_aluguel", nullable = false, insertable = true, updatable = true)
	private Integer idAluguel;

    @Column(name = "nu_avaliado", nullable = false, insertable = true, updatable = true)
	private Integer idAvaliado;

    @Column(name = "nu_avaliador", nullable = false, insertable = true, updatable = true)
	private Integer idAvaliador;

	public Integer getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(Integer idAluguel) {
		this.idAluguel = idAluguel;
	}

	public Integer getIdAvaliado() {
		return idAvaliado;
	}

	public void setIdAvaliado(Integer idAvaliado) {
		this.idAvaliado = idAvaliado;
	}

	public Integer getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(Integer idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dhAvaliacao == null) ? 0 : dhAvaliacao.hashCode());
		result = prime * result + ((idAluguel == null) ? 0 : idAluguel.hashCode());
		result = prime * result + ((idAvaliado == null) ? 0 : idAvaliado.hashCode());
		result = prime * result + ((idAvaliador == null) ? 0 : idAvaliador.hashCode());
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
		AvaliacaoPK other = (AvaliacaoPK) obj;
		if (dhAvaliacao == null) {
			if (other.dhAvaliacao != null)
				return false;
		} else if (!dhAvaliacao.equals(other.dhAvaliacao))
			return false;
		if (idAluguel == null) {
			if (other.idAluguel != null)
				return false;
		} else if (!idAluguel.equals(other.idAluguel))
			return false;
		if (idAvaliado == null) {
			if (other.idAvaliado != null)
				return false;
		} else if (!idAvaliado.equals(other.idAvaliado))
			return false;
		if (idAvaliador == null) {
			if (other.idAvaliador != null)
				return false;
		} else if (!idAvaliador.equals(other.idAvaliador))
			return false;
		return true;
	}
	
}
