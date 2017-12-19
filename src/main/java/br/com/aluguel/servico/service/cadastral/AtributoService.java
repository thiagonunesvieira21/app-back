package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Atributo;
import br.com.aluguel.servico.repository.cadastral.AtributoRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 30/06/17.
 */
@Service
public class AtributoService extends GenericService<Atributo, Integer> {

	private AtributoRepository repository;

    @Autowired
    public AtributoService(AtributoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Atributo> findByAnuncio(Integer idAnuncio, int page, int size){
    	return repository.findByIdAnuncio(idAnuncio, new PageRequest(page, size));
    }

}
