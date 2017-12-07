package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.math.BigDecimal;
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

import br.com.util.entity.AcessoUsuario;
import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "avaliacao", schema = "aluguel")
@ApiModel(value="Avaliacao")
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 5618337221619034933L;

	@EmbeddedId
	private AvaliacaoPK id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_avaliacao", nullable = false, insertable = false, updatable = false)
	private Date dhAvaliacao;

	@Column(name = "de_avaliacao", nullable = false)
	private String deAvaliacao;

	@Column(name = "vr_nota_avaliacao", nullable = false)
	private BigDecimal vrNotaAvaliacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_aluguel", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_avaliacao_aluguel"))
	private Aluguel aluguel;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_avaliado", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_avaliacao_avaliado"))
	private AcessoUsuario avaliado;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_avaliador", nullable = false, insertable = false, updatable = false, foreignKey=@ForeignKey(name="fk_avaliacao_avaliador"))
	private AcessoUsuario avaliador;

	public AvaliacaoPK getId() {
		return id;
	}

	public void setId(AvaliacaoPK id) {
		this.id = id;
	}

	public Date getDhAvaliacao() {
		return dhAvaliacao;
	}

	public void setDhAvaliacao(Date dhAvaliacao) {
		this.dhAvaliacao = dhAvaliacao;
	}

	public String getDeAvaliacao() {
		return deAvaliacao;
	}

	public void setDeAvaliacao(String deAvaliacao) {
		this.deAvaliacao = deAvaliacao;
	}

	public BigDecimal getVrNotaAvaliacao() {
		return vrNotaAvaliacao;
	}

	public void setVrNotaAvaliacao(BigDecimal vrNotaAvaliacao) {
		this.vrNotaAvaliacao = vrNotaAvaliacao;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public AcessoUsuario getAvaliado() {
		return avaliado;
	}

	public void setAvaliado(AcessoUsuario avaliado) {
		this.avaliado = avaliado;
	}

	public AcessoUsuario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(AcessoUsuario avaliador) {
		this.avaliador = avaliador;
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
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
