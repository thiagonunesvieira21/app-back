package br.com.aluguel.servico.criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.aluguel.entity.cadastral.aluguel.Imagem;

/**
 * Created by thiago on 30/06/17.
 */
@Component
public class ImagemCriteria {

    @PersistenceContext
    EntityManager em;

    private Criteria criteria = null;

    public Criteria getCriteria(Class<? extends Object> clazz){
        return getSession().createCriteria(clazz);
    }

    public Session getSession(){
        return em.unwrap(Session.class);
    }

    public Criteria getImagemCriteria() {

        this.criteria = getCriteria(Imagem.class);

        return criteria;
    }

    public void setAnuncio(Integer idAnuncio){
        this.criteria.add(Restrictions.eq("idAnuncio", idAnuncio));
    }
}
