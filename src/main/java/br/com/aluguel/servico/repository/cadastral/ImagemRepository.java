package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluguel.entity.cadastral.aluguel.Imagem;

/**
 * Created by thiago on 30/06/17.
 */
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
	
	@Query("SELECT i FROM Imagem i WHERE " +
            "i.idAnuncio = :idAnuncio ")
	Page<Imagem> findByIdAnuncio(@Param("idAnuncio") Integer idAnuncio, Pageable pageRequest);
	
}
