package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.aluguel.Locador;

public interface LocadorRepository extends JpaRepository<Locador, Integer> {

}
