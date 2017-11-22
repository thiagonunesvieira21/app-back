package br.com.aluguel.servico.criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aluguel.servico.security.SecurityUser;
import br.com.aluguel.servico.service.suporte.AcessoUsuarioService;

@Component
public class UsuarioCriteria {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	AcessoUsuarioService us;
	
	@Autowired HttpSession session;
	
	private Criteria criteria = null;
	private SecurityUser user = null;
	
	public Criteria getCriteria(Class<? extends Object> clazz){
		return getSession().createCriteria(clazz);
	}

	public Session getSession(){
		return em.unwrap(Session.class);
	}

}
