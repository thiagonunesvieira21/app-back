package br.com.aluguel.servico.service.suporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.suporte.AcessoFuncionalidade;
import br.com.aluguel.servico.repository.suporte.AcessoFuncionalidadeRepository;
import br.com.aluguel.servico.service.GenericService;

@Service
public class AcessoFuncionalidadeService extends GenericService<AcessoFuncionalidade, Integer>{

	@Autowired
	public AcessoFuncionalidadeService(AcessoFuncionalidadeRepository repo) {
		super(repo);
	}
}
