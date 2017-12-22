package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aluguel.entity.cadastral.aluguel.Categoria;

/**
 * Created by thiago on 29/06/17.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Query("SELECT c FROM Categoria c WHERE " +
            "c.idCategoriaPai = :idCategoriaPai ")
	Page<Categoria> findByIdCategoriaPai(@Param("idCategoriaPai") Integer idCategoriaPai, Pageable pageRequest);

}
