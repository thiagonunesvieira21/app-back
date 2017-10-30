package br.com.servico.service.cadastral;

import br.com.entity.cadastral.Deposito;
import br.com.servico.repository.cadastral.DepositoRepository;
import br.com.servico.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
