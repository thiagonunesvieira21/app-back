package br.com.aluguel.servico.service.cadastral;

import static br.com.util.utilities.MyHibernateUtils.listAndCast;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Categoria;
import br.com.aluguel.servico.criteria.CategoriaCriteria;
import br.com.aluguel.servico.repository.cadastral.CategoriaRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 29/06/17.
 */
@Service
public class CategoriaService extends GenericService<Categoria, Integer> {

	@Autowired
	private CategoriaCriteria categoriaCriteria;
	
    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }

	public List<Categoria> findByIdCategoriaPai(Integer idCategoriaPai) {
		Criteria criteria = categoriaCriteria.getCategoriaCriteria();
		categoriaCriteria.setCategoriaPai(idCategoriaPai);

        return listAndCast(criteria);
	}
}
