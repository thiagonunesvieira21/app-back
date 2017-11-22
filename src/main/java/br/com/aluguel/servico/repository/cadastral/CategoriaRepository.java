package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.Categoria;

/**
 * Created by thiago on 29/06/17.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
