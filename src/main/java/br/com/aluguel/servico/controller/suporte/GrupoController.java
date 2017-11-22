package br.com.aluguel.servico.controller.suporte;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguel.entity.suporte.AcessoGrupo;
import br.com.aluguel.exceptions.InvalidRequestException;
import br.com.aluguel.json.bean.suporte.CadastrarGrupo;
import br.com.aluguel.servico.controller.UtilController;
import br.com.aluguel.servico.security.SecurityUser;
import br.com.aluguel.servico.service.suporte.AcessoUsuarioService;
import br.com.aluguel.servico.service.suporte.GrupoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/grupo")
public class GrupoController extends UtilController {

	@Autowired
	private HttpSession session;

	@Autowired
	private GrupoService service;

	@Autowired
	private AcessoUsuarioService usuarioService;

	@ApiOperation(value = "Serviço responsável por cadastrar o grupo do usuário")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Secured({ "ROLE_MANTER_GRUPO" })
	public ResponseEntity<?> create(@RequestBody @Valid CadastrarGrupo model, BindingResult result) {

		if (result.hasErrors()) {
			throw new InvalidRequestException("Validação do cadastro de grupo", result);
		}

		AcessoGrupo acessoGrupo = new AcessoGrupo();
		acessoGrupo = prepareGrupo(model, acessoGrupo);

		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "Grupo cadastrado com sucesso");
		map.put("nuGrupo", acessoGrupo.getNuGrupo());
		return new ResponseEntity<>(map, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Serviço responsável por atualizar o grupo do usuário")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Secured({ "ROLE_MANTER_GRUPO" })
	public ResponseEntity<?> update(@RequestBody @Valid CadastrarGrupo grupo, @PathVariable Integer id,
			BindingResult result) {

		if (result.hasErrors()) {
			throw new InvalidRequestException("Validação do cadastro de grupo", result);
		}

		AcessoGrupo acessoGrupo = service.findById(id);

		if (acessoGrupo == null) {
			throw new NoResultException("Não existe o Grupo cadastrado com os dados inseridos.");
		}

		acessoGrupo = prepareGrupo(grupo, acessoGrupo);

		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "Grupo alterado com sucesso");
		map.put("nuGrupo", acessoGrupo.getNuGrupo());
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@ApiOperation(value = "Buscar todos os grupos da hierarquia do usuário", response = AcessoGrupo.class, notes = "Retorna todos os grupos cadastrados no sistema", responseContainer = "List")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Set<AcessoGrupo> getAllGroupsDaHierarquia() {
		return new HashSet<AcessoGrupo>(service.findAll());
	}

	@ApiOperation(value = "Buscar grupo pelo ID", response = AcessoGrupo.class, notes = "Retorna o grupo a partir do ID especificado")
	@ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AcessoGrupo getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	private AcessoGrupo prepareGrupo(CadastrarGrupo model, AcessoGrupo acessoGrupo) {

		SecurityUser user = getAthenticatedUser(session);

		BeanUtils.copyProperties(model, acessoGrupo, getNullPropertyNames(model));

		acessoGrupo = service.save(acessoGrupo);
		return acessoGrupo;
	}

}