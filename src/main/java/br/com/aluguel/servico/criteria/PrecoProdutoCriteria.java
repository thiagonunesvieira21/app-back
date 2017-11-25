package br.com.aluguel.servico.criteria;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.aluguel.entity.cadastral.PrecoProduto;

/**
 * Created by thiago on 11/07/17.
 */
@Component
public class PrecoProdutoCriteria {

    @PersistenceContext
    EntityManager em;

    private Criteria criteria = null;

    public Criteria getCriteria(Class<? extends Object> clazz){
        return getSession().createCriteria(clazz);
    }

    public Session getSession(){
        return em.unwrap(Session.class);
    }

    public Criteria getAtributoCriteria() {

        this.criteria = getCriteria(PrecoProduto.class);

        return criteria;
    }

    public void setProduto(Integer idProduto){
        this.criteria.add(Restrictions.eq("idProduto", idProduto));
    }

    public void setData(Date data){
        this.criteria.add(Restrictions.gt("dtInicio", data));
        this.criteria.add(Restrictions.ge("dtFim", data));
    }

}
