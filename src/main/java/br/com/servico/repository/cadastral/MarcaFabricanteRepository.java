package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.MarcaFabricante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 21/06/17.
 */
public interface MarcaFabricanteRepository extends JpaRepository<MarcaFabricante, Integer> {
}
