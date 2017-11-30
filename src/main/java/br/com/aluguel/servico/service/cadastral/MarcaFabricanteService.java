package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.MarcaFabricante;
import br.com.aluguel.servico.repository.cadastral.MarcaFabricanteRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 21/06/17.
 */
@Service
public class MarcaFabricanteService extends GenericService<MarcaFabricante, Integer> {

    @Autowired
    public MarcaFabricanteService(MarcaFabricanteRepository repository) {
        super(repository);
    }
}
