package br.com.servico.criteria;

import br.com.entity.cadastral.PrecoProduto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

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
