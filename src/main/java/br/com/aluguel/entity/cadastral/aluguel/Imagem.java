package br.com.aluguel.entity.cadastral.aluguel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

import io.swagger.annotations.ApiModel;

/**
 * Created by thiago on 20/11/15.
 */
@Entity
@XmlRootElement
@Table(name = "imagem", schema = "aluguel")
@SequenceGenerator(name = "image_seq", sequenceName = "aluguel.seq_nu_imagem", allocationSize = 1, initialValue = 1)
@ApiModel(value="Imagem")
public class Imagem implements Serializable {
	private static final long serialVersionUID = -5832663147596985205L;
	
	private Integer id;
    private String caminho;
    private String titulo;
    private Integer ordem;
    private Anuncio anuncio;
    private Integer idAnuncio;

    public Imagem() {
        this.ordem = 0;
    }

    @Id
    @Column(name = "nu_imagem")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 200)
    @Column(name = "de_imagem_caminho")
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    @NotNull
    @Size(max = 30)
    @Column(name = "de_imagem_titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotNull
    @Column(name = "nu_imagem_ordem")
    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nu_anuncio", referencedColumnName = "nu_anuncio", insertable = true, updatable = true)
    public Anuncio getAnuncio() {
    	return anuncio;
    }
    
    public void setAnuncio(Anuncio anuncio) {
    	this.anuncio = anuncio;
    }


    @Column(name = "nu_anuncio", insertable = false, updatable = false)
    public Integer getIdAnuncio() {
    	return idAnuncio;
    }
    
    public void setIdAnuncio(Integer idAnuncio) {
    	this.idAnuncio = idAnuncio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem = (Imagem) o;
        return id == imagem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
