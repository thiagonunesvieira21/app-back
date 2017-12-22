package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Pergunta;
import br.com.aluguel.entity.cadastral.aluguel.PerguntaPK;
import br.com.aluguel.servico.repository.cadastral.PerguntaRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 30/06/17.
 */
@Service
public class PerguntaService extends GenericService<Pergunta, PerguntaPK> {


	private PerguntaRepository repository;
	
    @Autowired
    public PerguntaService(PerguntaRepository repository) {
    	super(repository);
    	this.repository = repository;
    }

    public Page<Pergunta> findByIdAnuncio(Integer idAnuncio, int page, int size){
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhPergunta"));
    	return repository.findByIdAnuncio(idAnuncio, new PageRequest(page, size, sort));
    }

}
