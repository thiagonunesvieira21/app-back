package br.com.servico.service.cadastral;

import br.com.entity.cadastral.PrecoProduto;
import br.com.entity.cadastral.PrecoProdutoPK;
import br.com.servico.criteria.PrecoProdutoCriteria;
import br.com.servico.repository.cadastral.PrecoProdutoRepository;
import br.com.servico.service.GenericService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static br.com.servico.util.MyHibernateUtils.listAndCast;

/**
 * Created by thiago on 11/07/17.
 */
@Service
public class PrecoProdutoService extends GenericService<PrecoProduto, PrecoProdutoPK> {

    @Autowired
    PrecoProdutoCriteria precoProdutoCriteria;

    @Autowired
    public PrecoProdutoService(PrecoProdutoRepository repository) {
        super(repository);
    }

    public List<PrecoProduto> findByVirgecia(Date dataVirgencia, Integer idProduto){
        Criteria criteria = precoProdutoCriteria.getAtributoCriteria();
        precoProdutoCriteria.setProduto(idProduto);
        precoProdutoCriteria.setData(dataVirgencia);

        return listAndCast(criteria);
    }
}
