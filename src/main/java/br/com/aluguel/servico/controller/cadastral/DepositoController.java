package br.com.aluguel.servico.controller.cadastral;

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

import br.com.aluguel.entity.cadastral.Deposito;
import br.com.aluguel.json.bean.cadastral.CadastrarDeposito;
import br.com.aluguel.servico.service.cadastral.DepositoService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/deposito")
public class DepositoController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private DepositoService service;

    @ApiOperation(value = "Serviço responsável por cadastrar o deposito")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_DEPOSITO" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarDeposito model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de deposito", result);
        }

        Deposito categoria = new Deposito();
        categoria = prepareDeposito(model, categoria);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Deposito cadastrado com sucesso");
        map.put("id", categoria.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por atualizar o deposito")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_DEPOSITO" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarDeposito model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de deposito", result);
        }

        Deposito categoria = service.findById(id);

        if (categoria == null) {
            throw new NoResultException("Não existe deposito cadastrado com os dados inseridos.");
        }

        categoria = prepareDeposito(model, categoria);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Deposito alterado com sucesso");
        map.put("id", categoria.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar todas os depositos", response = Deposito.class, notes = "Retorna todos os depositos cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Deposito> getAll() {
        return new HashSet<Deposito>(service.findAll());
    }

    @ApiOperation(value = "Buscar deposito pelo ID", response = Deposito.class, notes = "Retorna o deposito a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Deposito getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private Deposito prepareDeposito(CadastrarDeposito model, Deposito categoria) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, categoria, getNullPropertyNames(model));

        categoria = service.save(categoria);
        return categoria;
    }
}
