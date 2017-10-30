package br.com.servico.controller.suporte;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.suporte.AcessoGrupo;
import br.com.entity.suporte.AcessoUsuario;
import br.com.exceptions.InvalidRequestException;
import br.com.json.bean.suporte.VincularGrupoUsuario;
import br.com.servico.controller.UtilController;
import br.com.servico.security.SecurityUser;
import br.com.servico.service.suporte.AcessoUsuarioService;
import br.com.servico.service.suporte.GrupoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/grupousuario")
public class GrupoUsuarioController extends UtilController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private GrupoService agService;
	
	@Autowired
	private AcessoUsuarioService usuarioService;
	
	@ApiOperation(value="Serviço responsável por associar os grupos aos Usuários.")
	@ApiImplicitParam(paramType="header", name=AUTH_HEADER_NAME, value="API Key")
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> delete(@RequestBody @Valid VincularGrupoUsuario model, BindingResult result){		
		return saveOrDelete(model, result, false);
	}
	
	@ApiOperation(value="Serviço responsável por associar os grupos aos Usuários.")
	@ApiImplicitParam(paramType="header", name=AUTH_HEADER_NAME, value="API Key")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid VincularGrupoUsuario model, BindingResult result){		
		return saveOrDelete(model, result, true);
	}
	
	private ResponseEntity<?> saveOrDelete(VincularGrupoUsuario model, BindingResult result,boolean save){
		if(model.getIdGrupos().length == 0){
			throw new InvalidRequestException("Solicite a inclusão/deleção de ao menos um Grupo ao Usuário.", result);
		}
		AcessoUsuario userAddGroups = usuarioService.findById(model.getIdUsuario());
		if(userAddGroups == null){
			throw new InvalidRequestException("Usuário solicitado para adicionar Grupos não existe.", result);
		}
		
		SecurityUser user = getAthenticatedUser(session);
		
		Set<AcessoGrupo> grupos = new HashSet<AcessoGrupo>();
//		grupos.addAll(userAddGroups.getAcessoGrupos());
		
		for(Integer i: model.getIdGrupos()){
			AcessoGrupo ag = agService.findById(i);
			if(ag == null){
				throw new InvalidRequestException("Grupo Solicitado não existe.", result);
			}
			if(!user.getAcessoGrupos().contains(ag)){
				throw new AccessDeniedException("Você não pode associar um grupo do qual não pertence.");
			}
			if(save){
				if(!grupos.add(ag)){
					throw new InvalidRequestException("Solicitada inclusão de Grupo(s) que o Usuário já faz parte.", result);
				}
			}else{
				if(!grupos.remove(ag)){
					throw new InvalidRequestException("Solicitada exclusão de Grupo(s)  que o Usuário não faz parte.", result);
				}
			}
		}
		userAddGroups.getAcessoGrupos().clear();
		userAddGroups.getAcessoGrupos().addAll(grupos);
		usuarioService.save(userAddGroups);
		HashMap<String, Object> map = new HashMap<>();
		if(save){
			map.put("msg", "Grupos Adicionados ao Usuário " + userAddGroups.getNome());
		}else{
			map.put("msg", "Grupos Removidos do Usuário " + userAddGroups.getNome());
		}
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
}
