package br.com.servico.service.suporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entity.suporte.AcessoFuncionalidade;
import br.com.servico.repository.suporte.AcessoFuncionalidadeRepository;
import br.com.servico.service.GenericService;

@Service
public class AcessoFuncionalidadeService extends GenericService<AcessoFuncionalidade, Integer>{

	@Autowired
	public AcessoFuncionalidadeService(AcessoFuncionalidadeRepository repo) {
		super(repo);
	}
}
