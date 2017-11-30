package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Produto;
import br.com.aluguel.servico.repository.cadastral.ProdutoRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class ProdutoService extends GenericService<Produto, Integer> {


    ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page<Produto> findPaginated(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }
}
