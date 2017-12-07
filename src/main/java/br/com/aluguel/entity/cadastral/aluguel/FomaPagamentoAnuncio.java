package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "forma_pagamento_anuncio", schema = "aluguel")
@ApiModel(value="FomaPagamentoAnuncio")
public class FomaPagamentoAnuncio implements Serializable {
	private static final long serialVersionUID = 1409720708903194673L;

	@EmbeddedId
	private FomaPagamentoAnuncioPK id;
	
	@Column(name = "qt_parcela", nullable = false, insertable = false, updatable = false)
	private Integer qtParcela;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_anuncio", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_forma_pagamento_anuncio_anuncio"))
	private Anuncio anuncio;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_forma_pagamento", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_forma_pagamento_anuncio_forma_pagamento"))
	private FormaPagamento formaPagamento;
	
	public FomaPagamentoAnuncioPK getId() {
		return id;
	}

	public void setId(FomaPagamentoAnuncioPK id) {
		this.id = id;
	}

	public Integer getQtParcela() {
		return qtParcela;
	}

	public void setQtParcela(Integer qtParcela) {
		this.qtParcela = qtParcela;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
		FomaPagamentoAnuncio other = (FomaPagamentoAnuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
