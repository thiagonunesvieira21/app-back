package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FomaPagamentoAnuncioPK implements Serializable {
	private static final long serialVersionUID = -5053422859708466364L;

    @Column(name = "qt_parcela", nullable = false, insertable = true, updatable = true)
	private Integer qtParcela;

    @Column(name = "nu_anuncio", nullable = false, insertable = true, updatable = true)
	private Integer idAnuncio;

    @Column(name = "nu_forma_pagamento", nullable = false, insertable = true, updatable = true)
	private Integer idFormaPagamento;

	public Integer getQtParcela() {
		return qtParcela;
	}

	public void setQtParcela(Integer qtParcela) {
		this.qtParcela = qtParcela;
	}

	public Integer getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Integer idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAnuncio == null) ? 0 : idAnuncio.hashCode());
		result = prime * result + ((idFormaPagamento == null) ? 0 : idFormaPagamento.hashCode());
		result = prime * result + ((qtParcela == null) ? 0 : qtParcela.hashCode());
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
		FomaPagamentoAnuncioPK other = (FomaPagamentoAnuncioPK) obj;
		if (idAnuncio == null) {
			if (other.idAnuncio != null)
				return false;
		} else if (!idAnuncio.equals(other.idAnuncio))
			return false;
		if (idFormaPagamento == null) {
			if (other.idFormaPagamento != null)
				return false;
		} else if (!idFormaPagamento.equals(other.idFormaPagamento))
			return false;
		if (qtParcela == null) {
			if (other.qtParcela != null)
				return false;
		} else if (!qtParcela.equals(other.qtParcela))
			return false;
		return true;
	}
	
}
