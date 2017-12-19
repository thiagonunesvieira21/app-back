package br.com.aluguel.servico.service.cadastral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.cadastral.aluguel.Locatario;
import br.com.aluguel.servico.repository.cadastral.LocatarioRepository;
import br.com.util.service.GenericService;

@Service
public class LocatarioService extends GenericService<Locatario, Integer> {

	@Autowired
	public LocatarioService(LocatarioRepository repository) {
		super(repository);
	}

}
