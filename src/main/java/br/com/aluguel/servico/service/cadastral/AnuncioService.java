package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;
import br.com.aluguel.servico.criteria.AnuncioCriteria;
import br.com.aluguel.servico.repository.cadastral.AnuncioRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class AnuncioService extends GenericService<Anuncio, Integer> {


    AnuncioRepository repository;
    
    @Autowired
    AnuncioCriteria anuncioCriteria;

    @Autowired
    public AnuncioService(AnuncioRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Anuncio> findPaginated(int page, int size) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhAnuncio"));
        return repository.findAll(new PageRequest(page, size, sort));
    }
    
    public Page<Anuncio> findByIdCategoria(Integer idCategoria, int page, int size) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhAnuncio"));
    	return repository.findByIdCategoria(idCategoria, new PageRequest(page, size, sort));
    }
    
    public Page<Anuncio> findByCoUf(String coUf, int page, int size) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhAnuncio"));
    	return repository.findByCoUf(coUf, new PageRequest(page, size, sort));
    }
    
    public Page<Anuncio> findByCoMunicipio(String coMunicipio, int page, int size) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhAnuncio"));
    	return repository.findByCoMunicipio(coMunicipio, new PageRequest(page, size, sort));
    }
    
    public Page<Anuncio> findByIdSituacao(Integer idSituacao, int page, int size) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "dhAnuncio"));
    	return repository.findByIdSituacao(idSituacao, new PageRequest(page, size, sort));
    }
}
