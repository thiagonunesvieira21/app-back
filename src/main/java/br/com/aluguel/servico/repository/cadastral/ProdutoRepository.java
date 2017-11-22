package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.Produto;

/**
 * Created by thiago on 29/06/17.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
