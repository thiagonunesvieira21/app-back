package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.aluguel.Locatario;

public interface LocatarioRepository extends JpaRepository<Locatario, Integer> {

}
