package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.Estoque;
import br.com.entity.cadastral.EstoquePK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 01/07/17.
 */
public interface EstoqueRepository extends JpaRepository<Estoque, EstoquePK> {
}
