package br.com.servico.service.cadastral;

import static br.com.servico.util.MyHibernateUtils.listAndCast;
import br.com.entity.cadastral.Atributo;
import br.com.servico.criteria.AtributoCriteria;
import br.com.servico.repository.cadastral.AtributoRepository;
import br.com.servico.service.GenericService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
