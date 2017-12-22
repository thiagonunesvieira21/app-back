package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluguel.entity.cadastral.aluguel.Pergunta;
import br.com.aluguel.entity.cadastral.aluguel.PerguntaPK;

/**
 * Created by thiago on 30/06/17.
 */
public interface PerguntaRepository extends JpaRepository<Pergunta, PerguntaPK> {
	
	@Query("SELECT p FROM Pergunta p WHERE " +
            "p.id.idAnuncio = :idAnuncio ")
	Page<Pergunta> findByIdAnuncio(@Param("idAnuncio") Integer idAnuncio, Pageable pageRequest);
	
}
