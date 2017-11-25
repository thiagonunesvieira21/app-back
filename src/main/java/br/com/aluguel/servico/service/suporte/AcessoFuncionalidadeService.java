package br.com.aluguel.servico.service.suporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.servico.service.GenericService;
import br.com.util.entity.AcessoFuncionalidade;
import br.com.util.repository.AcessoFuncionalidadeRepository;

@Service
public class AcessoFuncionalidadeService extends GenericService<AcessoFuncionalidade, Integer>{

	@Autowired
	public AcessoFuncionalidadeService(AcessoFuncionalidadeRepository repo) {
		super(repo);
	}
}
