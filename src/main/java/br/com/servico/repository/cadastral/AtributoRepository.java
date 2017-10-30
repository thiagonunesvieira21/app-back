package br.com.servico.repository.cadastral;

import br.com.entity.cadastral.Atributo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by thiago on 30/06/17.
 */
public interface AtributoRepository extends JpaRepository<Atributo, Integer> {
}
