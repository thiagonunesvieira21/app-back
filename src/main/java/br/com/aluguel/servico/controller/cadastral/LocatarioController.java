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

import br.com.aluguel.entity.cadastral.aluguel.Locatario;
import br.com.aluguel.servico.service.cadastral.LocatarioService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/locatario")
public class LocatarioController extends UtilController {

	@Autowired
	private LocatarioService service;
	
    @Autowired
    private HttpSession session;

    @ApiOperation(value = "Serviço responsável por cadastar o usuario como locatario ")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_REGISTRAR_LOCATARIO" })
    public ResponseEntity<?> create(BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de locatario", result);
        }

        Locatario locador = new Locatario();
        locador = prepareLocatario(locador);
       
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Locatario cadastrado com sucesso");
        map.put("id", locador.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Buscar locatario pelo ID", response = Locatario.class, notes = "Retorna a locatario a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Locatario getById(@PathVariable Integer id) {
        return service.findById(id);
    }
    
    private Locatario prepareLocatario(Locatario locatario) {

        SecurityUser user = getAthenticatedUser(session);

        locatario.setAcessoUsuario(user);

        Date dhCadastro = new Date(System.currentTimeMillis());
        
		locatario.setDhCadastro(dhCadastro);
        
        locatario = service.save(locatario);
        return locatario;
    }
}
