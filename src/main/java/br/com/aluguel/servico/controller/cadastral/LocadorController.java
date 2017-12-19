package br.com.aluguel.servico.controller.cadastral;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguel.entity.cadastral.aluguel.Imagem;
import br.com.aluguel.entity.cadastral.aluguel.Locador;
import br.com.aluguel.servico.service.cadastral.LocadorService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/locador")
public class LocadorController extends UtilController {

	@Autowired
	private LocadorService service;
	
    @Autowired
    private HttpSession session;

    @ApiOperation(value = "Serviço responsável por cadastar o usuario como locador ")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_REGISTRAR_LOCADOR" })
    public ResponseEntity<?> create(BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de locador", result);
        }

        Locador locador = new Locador();
        locador = prepareLocador(locador);
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Locador cadastrado com sucesso");
        map.put("id", locador.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Buscar locador pelo ID", response = Locador.class, notes = "Retorna a locador a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Locador getById(@PathVariable Integer id) {
        return service.findById(id);
    }
    
    private Locador prepareLocador(Locador locador) {

        SecurityUser user = getAthenticatedUser(session);

        locador.setAcessoUsuario(user);

        Date dhCadastro = new Date(System.currentTimeMillis());
        
		locador.setDhCadastro(dhCadastro);
        
        locador = service.save(locador);
        return locador;
    }
}
