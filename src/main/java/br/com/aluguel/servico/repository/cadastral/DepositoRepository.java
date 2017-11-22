package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.Deposito;

/**
 * Created by thiago on 30/06/17.
 */
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {
}
