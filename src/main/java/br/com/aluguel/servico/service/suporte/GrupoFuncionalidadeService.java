package br.com.aluguel.servico.service.suporte;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguel.entity.suporte.AcessoFuncionalidade;
import br.com.aluguel.entity.suporte.AcessoGrupo;
import br.com.aluguel.entity.suporte.AcessoGrupoFuncionalidade;
import br.com.aluguel.entity.suporte.AcessoGrupoFuncionalidadeId;
import br.com.aluguel.servico.repository.suporte.AcessoGrupoFuncionalidadeRepository;
import br.com.aluguel.servico.service.GenericService;

@Service
public class GrupoFuncionalidadeService extends GenericService<AcessoGrupoFuncionalidade, AcessoGrupoFuncionalidadeId> {
	
	@Autowired
	private GrupoService service;
	
	@Autowired
	public GrupoFuncionalidadeService(AcessoGrupoFuncionalidadeRepository repo){
		super(repo);
	}
	
	public boolean hasNotPermissaoGrupoPai(AcessoGrupo grupo, AcessoFuncionalidade f) {
		 AcessoGrupo grupoPai =  service.findById(grupo.getNuGrupoPai());
		AcessoGrupoFuncionalidade agfPai = new AcessoGrupoFuncionalidade(new AcessoGrupoFuncionalidadeId
				(grupoPai.getNuGrupo(),f.getIdFuncionalidade()),f,grupoPai,true);
		
		if(!grupoPai.getAcessoGrupoFuncionalidades().contains(agfPai)){
			return true;
		}
		return false;
	}
	
	public void checkAlteraPropagavel(Set<AcessoGrupoFuncionalidade> funcionalidades, AcessoGrupoFuncionalidade agf) {
		AcessoGrupoFuncionalidade changePropagavel = super.findById(agf.getId());
		if(changePropagavel != null && agf.isPropagavel() != changePropagavel.isPropagavel()){
			funcionalidades.remove(changePropagavel);
		}
	}

}