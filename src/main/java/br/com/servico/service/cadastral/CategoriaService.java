package br.com.servico.service.cadastral;

import br.com.entity.cadastral.Categoria;
import br.com.servico.repository.cadastral.CategoriaRepository;
import br.com.servico.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class CategoriaService extends GenericService<Categoria, Integer> {

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }
}
