package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.UnidadeMedida;

/**
 * Created by thiago on 28/06/17.
 */
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Integer> {
}
