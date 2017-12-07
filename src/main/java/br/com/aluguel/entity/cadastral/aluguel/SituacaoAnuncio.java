package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "situacao_anuncio", schema = "aluguel")
@SequenceGenerator(name = "situacao_anuncio_seq", sequenceName = "aluguel.seq_situacao_anuncio", allocationSize = 1, initialValue = 1)
@ApiModel(value="SituacaoAnuncio")
public class SituacaoAnuncio implements Serializable{
	private static final long serialVersionUID = 4271023996839723544L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "situacao_anuncio_seq")
    @Column(name = "nu_situacao_anuncio")
	private Integer id;

	@Column(name = "no_situacao_anuncio", nullable=false)
	private String noSituacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoSituacao() {
		return noSituacao;
	}

	public void setNoSituacao(String noSituacao) {
		this.noSituacao = noSituacao;
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
		SituacaoAnuncio other = (SituacaoAnuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
