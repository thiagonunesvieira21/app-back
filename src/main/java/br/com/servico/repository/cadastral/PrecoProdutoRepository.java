package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.PrecoProduto;
import br.com.entity.cadastral.PrecoProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 11/07/17.
 */
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, PrecoProdutoPK> {
}
