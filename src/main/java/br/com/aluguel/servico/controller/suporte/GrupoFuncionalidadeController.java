package br.com.aluguel.servico.controller.suporte;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguel.servico.service.suporte.AcessoFuncionalidadeService;
import br.com.aluguel.servico.service.suporte.GrupoFuncionalidadeService;
import br.com.aluguel.servico.service.suporte.GrupoService;
import br.com.util.controller.UtilController;
import br.com.util.entity.AcessoFuncionalidade;
import br.com.util.entity.AcessoGrupo;
import br.com.util.entity.AcessoGrupoFuncionalidade;
import br.com.util.entity.AcessoGrupoFuncionalidadeId;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.json.bean.FuncionalidadePropagavel;
import br.com.util.json.bean.VincularGrupoFuncionalidade;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/grupofuncionalidade")
public class GrupoFuncionalidadeController extends UtilController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AcessoFuncionalidadeService afService;
	
	@Autowired
	GrupoFuncionalidadeService gfService;
	
	@Autowired
	private GrupoService service;
	
	@ApiOperation(value="Serviço responsável por associar os grupos as funcionalidades do sistema.")
	@ApiImplicitParam(paramType="header", name=AUTH_HEADER_NAME, value="API Key")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Secured({"ROLE_MANTER_GRUPO"})
	public ResponseEntity<?> save(@RequestBody @Valid VincularGrupoFuncionalidade model, BindingResult result){
		return saveOrDelete(model,result,true);		
	}
	
	@ApiOperation(value="Serviço responsável por desassociar as funcionalidades dos grupos do sistema")
	@ApiImplicitParam(paramType="header", name=AUTH_HEADER_NAME, value="API Key")
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Secured({"ROLE_MANTER_GRUPO"})
	public ResponseEntity<?> delete(@RequestBody VincularGrupoFuncionalidade model, BindingResult result){
		return saveOrDelete(model,result,false);
	}
	
	private ResponseEntity<?> saveOrDelete(VincularGrupoFuncionalidade model, BindingResult result,boolean save){
		
		notEmptyFuncionalidades(model.getFuncionalidades(), result);
		
		AcessoGrupo grupo = getGrupoNotNull(model.getIdGrupo(),result);
		
		validaPermissaoUsuario(grupo);
		
		Set<AcessoGrupoFuncionalidade> funcionalidades = new HashSet<AcessoGrupoFuncionalidade>();
		funcionalidades.addAll(grupo.getAcessoGrupoFuncionalidades());		
		
		for(FuncionalidadePropagavel fp : model.getFuncionalidades()){
			AcessoFuncionalidade f = getFuncionalidade(fp);

			checkFuncionalidade(result, f);
			
			AcessoGrupoFuncionalidade agf = getGrupoFuncionalidade(grupo, fp, f);
			
			if(save){
				
				if(gfService.hasNotPermissaoGrupoPai(grupo, f)) {
					throw new InvalidRequestException("Permissão Negada: Grupo Pai não Posssui esta funcionalidade e/ou esta não é propagável", result);
				}
				
				checkAlteraPropagavel(funcionalidades, agf);

			}
		}
		
		grupo.getAcessoGrupoFuncionalidades().clear();
		grupo.getAcessoGrupoFuncionalidades().addAll(funcionalidades);
		service.save(grupo);
		
		HashMap<String, Object> map = new HashMap<>();
		if(save){
			map.put("msg", "Funcionalidades incluidas no grupo " + grupo.getDeGrupo());
		}else{
			map.put("msg", "Funcionalidades removidas no grupo " + grupo.getDeGrupo());
		}
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	private void checkFuncionalidade(BindingResult result, AcessoFuncionalidade f) {
		if(f == null){
			throw new InvalidRequestException("Não é possível associar/desassociar: Funcionalidade solicitada não existe.", result);
		}
	}

	private void checkAlteraPropagavel(Set<AcessoGrupoFuncionalidade> funcionalidades, AcessoGrupoFuncionalidade agf) {
		AcessoGrupoFuncionalidade changePropagavel = gfService.findById(agf.getId());
		if(changePropagavel != null && agf.isPropagavel() != changePropagavel.isPropagavel()){
			funcionalidades.remove(changePropagavel);
		}
	}


	private AcessoFuncionalidade getFuncionalidade(FuncionalidadePropagavel fp) {
		AcessoFuncionalidade f =  afService.findById(fp.getIdFuncionalidade());
		return f;
	}

	private void validaPermissaoUsuario(AcessoGrupo grupo) {
		SecurityUser user = getAthenticatedUser(session);
		List<AcessoGrupo> userGroups = service.findAll();
		
		if(!userGroups.contains(grupo)) {
			throw new AccessDeniedException("Permissão Negada: Você deve pertencer ao Grupo que deseja adicionar funcionalidades.");
		}
		
	}

	private void notEmptyFuncionalidades(List<FuncionalidadePropagavel> list, BindingResult result) {
		if(list == null || list.isEmpty()){
			throw new InvalidRequestException("Solicite a inclusão/deleção de ao menos uma Funcionalidade ao Grupo", result);
		}
	}

	private AcessoGrupo getGrupoNotNull(Integer id,BindingResult result) {
		AcessoGrupo grupo = service.findById(id);
		if(grupo == null){
			throw new InvalidRequestException("Grupo inexistente.", result);
		}
		return grupo;
	}

	private AcessoGrupoFuncionalidade getGrupoFuncionalidade(AcessoGrupo grupo, FuncionalidadePropagavel fp,
			AcessoFuncionalidade f) {
		AcessoGrupoFuncionalidade agf = new AcessoGrupoFuncionalidade(new AcessoGrupoFuncionalidadeId
				(grupo.getNuGrupo(),f.getIdFuncionalidade()),f,grupo,fp.getPropagavel());
		return agf;
	}

	
	
}
