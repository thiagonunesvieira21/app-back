package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Imagem;
import br.com.aluguel.servico.repository.cadastral.ImagemRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 30/06/17.
 */
@Service
public class ImagemService extends GenericService<Imagem, Integer> {


	private ImagemRepository repository;
	
    @Autowired
    public ImagemService(ImagemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Imagem> findByIdAnuncio(Integer idAnuncio, int page, int size){
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "ordem"));
    	return repository.findByIdAnuncio(idAnuncio, new PageRequest(page, size, sort));
    }

}
