package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Locador;
import br.com.aluguel.servico.repository.cadastral.LocadorRepository;
import br.com.util.service.GenericService;

@Service
public class LocadorService extends GenericService<Locador, Integer> {

	@Autowired
	public LocadorService(LocadorRepository repository) {
		super(repository);
	}

}
