package br.com.aluguel.servico.repository.cadastral;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aluguel.entity.cadastral.aluguel.Imagem;

/**
 * Created by thiago on 30/06/17.
 */
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
}
