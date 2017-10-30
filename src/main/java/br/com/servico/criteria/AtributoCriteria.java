package br.com.servico.criteria;

import br.com.entity.cadastral.Atributo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by thiago on 30/06/17.
 */
@Component
public class AtributoCriteria {

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

        this.criteria = getCriteria(Atributo.class);

        return criteria;
    }

    public void setProduto(Integer idProduto){
        this.criteria.add(Restrictions.eq("idProduto", idProduto));
    }
}
