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
@Table(name = "forma_pagamento", schema = "aluguel")
@SequenceGenerator(name = "forma_pagamento_seq", sequenceName = "aluguel.seq_nu_forma_pagamento", allocationSize = 1, initialValue = 1)
@ApiModel(value="FormaPagamento")
public class FormaPagamento implements Serializable{
	private static final long serialVersionUID = -5293732510619051991L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forma_pagamento_seq")
    @Column(name = "nu_forma_pagamento")
	private Integer id;

	@Column(name = "no_forma_pagamento", nullable=false)
	private String noFormaPagamento;

	@Column(name = "ic_forma_pagamento", nullable=false)
	private String icTipoPagamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoFormaPagamento() {
		return noFormaPagamento;
	}

	public void setNoFormaPagamento(String noFormaPagamento) {
		this.noFormaPagamento = noFormaPagamento;
	}

	public String getIcTipoPagamento() {
		return icTipoPagamento;
	}

	public void setIcTipoPagamento(String icTipoPagamento) {
		this.icTipoPagamento = icTipoPagamento;
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
		FormaPagamento other = (FormaPagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
