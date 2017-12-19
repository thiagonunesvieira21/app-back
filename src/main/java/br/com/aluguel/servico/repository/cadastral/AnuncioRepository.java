package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;

/**
 * Created by thiago on 29/06/17.
 */
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
	
	@Query("SELECT a FROM Anuncio a WHERE " +
	            "a.idCategoria = :idCategoria OR " +
	            "a.categoria.idCategoriaPai = :idCategoria " +
	            "ORDER BY a.dhAnuncio")
	Page<Anuncio> findByIdCategoria(@Param("idCategoria") Integer idCategoria,  PageRequest page);

	@Query("SELECT a FROM Anuncio a WHERE " +
            "a.coUf = :coUf " +
            "ORDER BY a.dhAnuncio")
	Page<Anuncio> findByCoUf(@Param("coUf") String coUf, PageRequest pageRequest);
	
	@Query("SELECT a FROM Anuncio a WHERE " +
            "a.coMunicipio = :coMunicipio " +
            "ORDER BY a.dhAnuncio")
	Page<Anuncio> findByCoMunicipio(@Param("coMunicipio") String coMunicipio, PageRequest pageRequest);

	@Query("SELECT a FROM Anuncio a WHERE " +
            "a.idSituacao = :idSituacao " +
            "ORDER BY a.dhAnuncio")
	Page<Anuncio> findByIdSituacao(@Param("idSituacao") Integer idSituacao, PageRequest pageRequest);
}
