package br.com.aluguel.servico.criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;

/**
 * Created by thiago on 30/06/17.
 */
@Component
public class AnuncioCriteria {

    @PersistenceContext
    EntityManager em;

    private Criteria criteria = null;

    public Criteria getCriteria(Class<? extends Object> clazz){
        return getSession().createCriteria(clazz);
    }

    public Session getSession(){
        return em.unwrap(Session.class);
    }

    public Criteria getAnuncioCriteria() {

        this.criteria = getCriteria(Anuncio.class);

        return criteria;
    }

    public void setCategoria(Integer idCategoria){
        this.criteria.add(Restrictions.eq("idCategoria", idCategoria));
    }
}
