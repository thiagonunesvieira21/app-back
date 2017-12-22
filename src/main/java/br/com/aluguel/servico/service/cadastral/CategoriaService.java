package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Categoria;
import br.com.aluguel.servico.repository.cadastral.CategoriaRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class CategoriaService extends GenericService<Categoria, Integer> {

	CategoriaRepository repository;
	
    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
        this.repository = repository;
    }

	public Page<Categoria> findByIdCategoriaPai(Integer idCategoriaPai, int page, int size){
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "nome"));
		return repository.findByIdCategoriaPai(idCategoriaPai, new PageRequest(page, size, sort));
	}
}
