package br.com.aluguel.servico.service.cadastral;

import static br.com.util.utilities.MyHibernateUtils.listAndCast;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.Atributo;
import br.com.aluguel.servico.criteria.AtributoCriteria;
import br.com.aluguel.servico.repository.cadastral.AtributoRepository;
import br.com.util.service.GenericService;

/**
 * Created by thiago on 30/06/17.
 */
@Service
public class AtributoService extends GenericService<Atributo, Integer> {

    @Autowired
    AtributoCriteria atributoCriteria;

    @Autowired
    public AtributoService(AtributoRepository repository) {
        super(repository);
    }

    public List<Atributo> findByProduto(Integer idProduto){
        Criteria criteria = atributoCriteria.getAtributoCriteria();
        atributoCriteria.setProduto(idProduto);

        return listAndCast(criteria);
    }

}
