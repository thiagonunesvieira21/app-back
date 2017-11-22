package br.com.aluguel.servico.service.cadastral;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.PrecoProduto;
import br.com.aluguel.entity.cadastral.PrecoProdutoPK;
import br.com.aluguel.servico.criteria.PrecoProdutoCriteria;
import br.com.aluguel.servico.repository.cadastral.PrecoProdutoRepository;
import br.com.aluguel.servico.service.GenericService;

import static br.com.aluguel.servico.util.MyHibernateUtils.listAndCast;

import java.util.Date;
import java.util.List;

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
