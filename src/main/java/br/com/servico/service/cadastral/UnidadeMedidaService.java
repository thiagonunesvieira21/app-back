package br.com.servico.service.cadastral;

import br.com.entity.cadastral.UnidadeMedida;
import br.com.servico.repository.cadastral.UnidadeMedidaRepository;
import br.com.servico.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
