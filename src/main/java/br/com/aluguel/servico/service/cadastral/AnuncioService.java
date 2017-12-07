package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;
import br.com.aluguel.servico.repository.cadastral.AnuncioRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class AnuncioService extends GenericService<Anuncio, Integer> {


    AnuncioRepository repository;

    @Autowired
    public AnuncioService(AnuncioRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Anuncio> findPaginated(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }
}
