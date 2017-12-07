package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.util.entity.AcessoUsuario;
import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name = "locatario", schema = "aluguel")
@SequenceGenerator(name = "locatario_seq", sequenceName = "aluguel.seq_nu_locatario", allocationSize = 1, initialValue = 1)
@ApiModel(value="Locatario")
public class Locatario implements Serializable {
	private static final long serialVersionUID = -957467747020510793L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locatario_seq")
    @Column(name = "nu_locatario")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dh_cadastro", nullable=false)
	private Date dhCadastro;

	@Column(name = "vr_reputacao", nullable=false)
	private BigDecimal vrReputacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nu_usuario", nullable=false, foreignKey=@ForeignKey(name="fk_locatario_usuario"))
	private AcessoUsuario acessoUsuario;
	
	@OneToMany(mappedBy="locatario")
	private Set<Pergunta> pergunta;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDhCadastro() {
		return dhCadastro;
	}

	public void setDhCadastro(Date dhCadastro) {
		this.dhCadastro = dhCadastro;
	}

	public BigDecimal getVrReputacao() {
		return vrReputacao;
	}

	public void setVrReputacao(BigDecimal vrReputacao) {
		this.vrReputacao = vrReputacao;
	}

	public Set<Pergunta> getPergunta() {
		return pergunta;
	}

	public void setPergunta(Set<Pergunta> pergunta) {
		this.pergunta = pergunta;
	}
	
	public AcessoUsuario getAcessoUsuario() {
		return acessoUsuario;
	}

	public void setAcessoUsuario(AcessoUsuario acessoUsuario) {
		this.acessoUsuario = acessoUsuario;
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
		Locatario other = (Locatario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
