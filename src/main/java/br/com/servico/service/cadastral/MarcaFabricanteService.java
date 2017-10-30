package br.com.servico.service.cadastral;

import br.com.entity.cadastral.MarcaFabricante;
import br.com.servico.repository.cadastral.MarcaFabricanteRepository;
import br.com.servico.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
