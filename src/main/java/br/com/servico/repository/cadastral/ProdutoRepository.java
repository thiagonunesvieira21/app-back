package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 29/06/17.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
