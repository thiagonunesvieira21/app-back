package br.com.servico.service.cadastral;

import br.com.entity.cadastral.Estoque;
import br.com.entity.cadastral.EstoquePK;
import br.com.servico.repository.cadastral.EstoqueRepository;
import br.com.servico.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by thiago on 01/07/17.
 */
@Service
public class EstoqueService extends GenericService<Estoque, EstoquePK> {

    @Autowired
    public EstoqueService(EstoqueRepository repository) {
        super(repository);
    }
}
