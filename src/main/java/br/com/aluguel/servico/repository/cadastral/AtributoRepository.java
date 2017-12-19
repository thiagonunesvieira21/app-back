package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluguel.entity.cadastral.aluguel.Atributo;
import br.com.aluguel.entity.cadastral.aluguel.Imagem;

/**
 * Created by thiago on 30/06/17.
 */
public interface AtributoRepository extends JpaRepository<Atributo, Integer> {
	
	@Query("SELECT a FROM Atributo a WHERE " +
            "aidAnuncio = :idAnuncio " +
            "ORDER BY a.ordem")
	Page<Atributo> findByIdAnuncio(@Param("idAnuncio") Integer idAnuncio, PageRequest pageRequest);
	
}
