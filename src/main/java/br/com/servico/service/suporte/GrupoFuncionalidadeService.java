package br.com.servico.service.suporte;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entity.suporte.AcessoFuncionalidade;
import br.com.entity.suporte.AcessoGrupo;
import br.com.entity.suporte.AcessoGrupoFuncionalidade;
import br.com.entity.suporte.AcessoGrupoFuncionalidadeId;
import br.com.servico.repository.suporte.AcessoGrupoFuncionalidadeRepository;
import br.com.servico.service.GenericService;

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
