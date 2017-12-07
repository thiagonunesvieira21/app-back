package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "aluguel", schema = "aluguel")
@SequenceGenerator(name = "aluguel_seq", sequenceName = "aluguel.seq_nu_aluguel", allocationSize = 1, initialValue = 1)
@ApiModel(value="Aluguel")
public class Aluguel implements Serializable {
	private static final long serialVersionUID = -8312237587801372786L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluguel_seq")
    @Column(name = "nu_aluguel")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_locacao", nullable=false)
	private Date dhLocacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_prevista_devolucao", nullable=false)
	private Date dhPrevistaDevolucao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_devolucao")
	private Date dhDevolucao;

	@Column(name = "qt_alugado", nullable=false)
	private Integer qtAlugado;

	@Column(name = "vr_total", nullable=false)
	private BigDecimal vrTotal;

	@Column(name = "vr_parcela", nullable=false)
	private BigDecimal vrParcela;

	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "latitude_retirada", nullable=false)),
            @AttributeOverride(name = "longitude", column = @Column(name = "longitude_retirada", nullable=false))
    })
	private Point localizacaoRetirada;

	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "latitude_devolucao", nullable=false)),
            @AttributeOverride(name = "longitude", column = @Column(name = "longitude_devolucao", nullable=false))
    })
	private Point localizacaoDevolucao;

	@Column(name = "de_endereco_retirada", nullable=false)
	private String enderecoRetirada;

	@Column(name = "de_endereco_devolucao", nullable=false)
	private String enderecoDevoluca;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_anuncio", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_aluguel_anuncio"))
	private Anuncio anuncio;
	
	@Column(name = "nu_anuncio", nullable=false, insertable=true, updatable=true)
	private Integer idAnuncio;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_locatario", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_aluguel_locatario"))
	private Locatario locatario;
	
	@Column(name = "nu_locatario", nullable=false, insertable=true, updatable=true)
	private Integer idLocatario;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_forma_pagamento", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_aluguel_forma_pagamento"))
	private FormaPagamento formaPagamento;
	
	@Column(name = "nu_forma_pagamento", nullable=false, insertable=true, updatable=true)
	private Integer idFormaPagamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDhLocacao() {
		return dhLocacao;
	}

	public void setDhLocacao(Date dhLocacao) {
		this.dhLocacao = dhLocacao;
	}

	public Date getDhPrevistaDevolucao() {
		return dhPrevistaDevolucao;
	}

	public void setDhPrevistaDevolucao(Date dhPrevistaDevolucao) {
		this.dhPrevistaDevolucao = dhPrevistaDevolucao;
	}

	public Date getDhDevolucao() {
		return dhDevolucao;
	}

	public void setDhDevolucao(Date dhDevolucao) {
		this.dhDevolucao = dhDevolucao;
	}

	public Integer getQtAlugado() {
		return qtAlugado;
	}

	public void setQtAlugado(Integer qtAlugado) {
		this.qtAlugado = qtAlugado;
	}

	public BigDecimal getVrTotal() {
		return vrTotal;
	}

	public void setVrTotal(BigDecimal vrTotal) {
		this.vrTotal = vrTotal;
	}

	public BigDecimal getVrParcela() {
		return vrParcela;
	}

	public void setVrParcela(BigDecimal vrParcela) {
		this.vrParcela = vrParcela;
	}

	public Point getLocalizacaoRetirada() {
		return localizacaoRetirada;
	}

	public void setLocalizacaoRetirada(Point localizacaoRetirada) {
		this.localizacaoRetirada = localizacaoRetirada;
	}

	public Point getLocalizacaoDevolucao() {
		return localizacaoDevolucao;
	}

	public void setLocalizacaoDevolucao(Point localizacaoDevolucao) {
		this.localizacaoDevolucao = localizacaoDevolucao;
	}

	public String getEnderecoRetirada() {
		return enderecoRetirada;
	}

	public void setEnderecoRetirada(String enderecoRetirada) {
		this.enderecoRetirada = enderecoRetirada;
	}

	public String getEnderecoDevoluca() {
		return enderecoDevoluca;
	}

	public void setEnderecoDevoluca(String enderecoDevoluca) {
		this.enderecoDevoluca = enderecoDevoluca;
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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
		Aluguel other = (Aluguel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
