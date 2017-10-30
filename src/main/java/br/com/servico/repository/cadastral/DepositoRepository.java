package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 30/06/17.
 */
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {
}
