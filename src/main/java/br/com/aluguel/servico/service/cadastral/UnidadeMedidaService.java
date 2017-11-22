package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.UnidadeMedida;
import br.com.aluguel.servico.repository.cadastral.UnidadeMedidaRepository;
import br.com.aluguel.servico.service.GenericService;

/**
 * Created by thiago on 28/06/17.
 */
@Service
public class UnidadeMedidaService extends GenericService<UnidadeMedida, Integer> {

    @Autowired
    public UnidadeMedidaService(UnidadeMedidaRepository repository) {
        super(repository);
    }
}
