package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 29/06/17.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
