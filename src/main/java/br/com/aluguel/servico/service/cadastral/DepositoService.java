package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Deposito;
import br.com.aluguel.servico.repository.cadastral.DepositoRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 30/06/17.
 */

@Service
public class DepositoService extends GenericService<Deposito, Integer> {

    @Autowired
    public DepositoService(DepositoRepository repository) {
        super(repository);
    }

}
