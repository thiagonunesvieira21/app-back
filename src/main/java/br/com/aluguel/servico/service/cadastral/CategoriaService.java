package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Categoria;
import br.com.aluguel.servico.repository.cadastral.CategoriaRepository;
import br.com.aluguel.servico.service.GenericService;

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
