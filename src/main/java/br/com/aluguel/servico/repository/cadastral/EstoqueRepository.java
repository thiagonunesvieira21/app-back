package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.Estoque;
import br.com.aluguel.entity.cadastral.EstoquePK;

/**
 * Created by thiago on 01/07/17.
 */
public interface EstoqueRepository extends JpaRepository<Estoque, EstoquePK> {
}
