package br.com.aluguel.servico.controller.suporte;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguel.servico.service.suporte.AcessoFuncionalidadeService;
import br.com.aluguel.servico.service.suporte.GrupoService;
import br.com.util.controller.UtilController;
import br.com.util.entity.AcessoFuncionalidade;
import br.com.util.entity.AcessoGrupo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/funcionalidade")
public class FuncionalidadeController extends UtilController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private AcessoFuncionalidadeService service;
	
	@ApiOperation(value = "Serviço responsável por exibir as funcionalidades do grupo pai do usuário", response=AcessoFuncionalidade.class, responseContainer="List")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AcessoFuncionalidade> getAll(@PathVariable Integer id) throws AccessDeniedException {
		AcessoGrupo grupo = grupoService.findById(id);
		if(grupo == null) {
			throw new AccessDeniedException("Voçê não possui este grupo");
		}
		
		AcessoGrupo grupoPai = grupoService.findByNuGrupoPai(grupo.getNuGrupoPai(), id);
		List<AcessoFuncionalidade> funcionalidades = new ArrayList<>();
		if(grupoPai != null && grupoPai.getAcessoGrupoFuncionalidades() != null) {
			grupoPai.getAcessoGrupoFuncionalidades().forEach(it -> {
				if(it.isPropagavel()) {
					funcionalidades.add(it.getAcessoFuncionalidade());
				}
			});
		}
		
		Comparator<AcessoFuncionalidade> order = (e1, e2) -> Integer.compare(
	            e1.getIdFuncionalidade(), e2.getIdFuncionalidade());
		
		if(!funcionalidades.isEmpty()) {
			funcionalidades.sort(order);
		}
			
		return funcionalidades;
	}
	
	/*TODO
	 * serviço temporário apenas para conclusão da tela de vincular as funcionalidades ao grupo
	 * este serviço deve ser removido
	 */
	@ApiOperation(value = "Serviço responsável por exibir todas as funcionalidades do sistema", response=AcessoFuncionalidade.class, responseContainer="List")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AcessoFuncionalidade> getAllFuncionalidadesOfSystem()  {
		return service.findAll();
	}
	
	
}