package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;

/**
 * Created by thiago on 29/06/17.
 */
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
}
