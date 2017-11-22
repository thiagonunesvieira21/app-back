package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.MarcaFabricante;

/**
 * Created by thiago on 21/06/17.
 */
public interface MarcaFabricanteRepository extends JpaRepository<MarcaFabricante, Integer> {
}
