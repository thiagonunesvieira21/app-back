package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "anuncio", schema = "aluguel")
@SequenceGenerator(name = "anuncio_seq", sequenceName = "aluguel.seq_nu_anuncio", allocationSize = 1, initialValue = 1)
@ApiModel(value="Anuncio")
public class Anuncio implements Serializable {
	private static final long serialVersionUID = 6922996169381860070L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anuncio_seq")
    @Column(name = "nu_anuncio")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_anuncio", nullable=false)
	private Date dhAnuncio;

	@Column(name = "de_anuncio", nullable=false)
	private String deAnuncio;

	@Column(name = "qt_disponivel", nullable=false)
	private Integer qtDisponivel;

	@Column(name = "vr_unitario", nullable=false)
	private BigDecimal vrUnitario;
	
	@Column(name = "co_uf", nullable=false)
	private String coUf;
	
	@Column(name = "co_municipio", nullable=false)
	private String coMunicipio;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_locador", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_anuncio_locador"))
	private Locador locador;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_situacao", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_anuncio_situacao"))
	private SituacaoAnuncio situacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_categoria", insertable=false, updatable=false, foreignKey=@ForeignKey(name="fk_anuncio_categoria"))
	private Categoria categoria;

	@Column(name = "nu_locador", nullable=false , insertable=true, updatable=true)
	private Integer idLocador;

	@Column(name = "nu_situacao", nullable=false , insertable=true, updatable=true)
	private Integer idSituacao;
	
	@Column(name = "nu_categoria", nullable=false , insertable=true, updatable=true)
	private Integer idCategoria;
	
	@JsonIgnore
  	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Atributo> atributos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Imagem> imagems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDhAnuncio() {
		return dhAnuncio;
	}

	public void setDhAnuncio(Date dhAnuncio) {
		this.dhAnuncio = dhAnuncio;
	}

	public String getDeAnuncio() {
		return deAnuncio;
	}

	public void setDeAnuncio(String deAnuncio) {
		this.deAnuncio = deAnuncio;
	}

	public Integer getQtDisponivel() {
		return qtDisponivel;
	}

	public void setQtDisponivel(Integer qtDisponivel) {
		this.qtDisponivel = qtDisponivel;
	}

	public BigDecimal getVrUnitario() {
		return vrUnitario;
	}

	public void setVrUnitario(BigDecimal vrUnitario) {
		this.vrUnitario = vrUnitario;
	}

	public String getCoUf() {
		return coUf;
	}

	public void setCoUf(String coUf) {
		this.coUf = coUf;
	}

	public String getCoMunicipio() {
		return coMunicipio;
	}

	public void setCoMunicipio(String coMunicipio) {
		this.coMunicipio = coMunicipio;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
	}

	public SituacaoAnuncio getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAnuncio situacao) {
		this.situacao = situacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getIdLocador() {
		return idLocador;
	}

	public void setIdLocador(Integer idLocador) {
		this.idLocador = idLocador;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Set<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(Set<Atributo> atributos) {
		this.atributos = atributos;
	}

	public Set<Imagem> getImagems() {
		return imagems;
	}

	public void setImagems(Set<Imagem> imagems) {
		this.imagems = imagems;
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
		Anuncio other = (Anuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
