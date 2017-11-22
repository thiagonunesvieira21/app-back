package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.PrecoProduto;
import br.com.aluguel.entity.cadastral.PrecoProdutoPK;

/**
 * Created by thiago on 11/07/17.
 */
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, PrecoProdutoPK> {
}
