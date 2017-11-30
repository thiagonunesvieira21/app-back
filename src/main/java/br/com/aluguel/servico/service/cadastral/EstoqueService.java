package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Estoque;
import br.com.aluguel.entity.cadastral.EstoquePK;
import br.com.aluguel.servico.repository.cadastral.EstoqueRepository;
import br.com.util.service.GenericService;

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
