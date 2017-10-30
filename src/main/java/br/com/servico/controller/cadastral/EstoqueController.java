package br.com.servico.controller.cadastral;

import br.com.entity.cadastral.Estoque;
import br.com.entity.cadastral.EstoquePK;
import br.com.exceptions.InvalidRequestException;
import br.com.json.bean.cadastral.CadastrarEstoque;
import br.com.servico.controller.UtilController;
import br.com.servico.security.SecurityUser;
import br.com.servico.service.cadastral.EstoqueService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thiago on 01/07/17.
 */
@RestController
@RequestMapping(value = "/api/estoque")
public class EstoqueController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private EstoqueService service;

    @ApiOperation(value = "Serviço responsável por cadastrar o estoque")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_ESTOQUE" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarEstoque model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de estoque", result);
        }

        Estoque estoque = new Estoque();
        estoque = prepareEstoque(model, estoque);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Estoque cadastrado com sucesso");
        map.put("id", new EstoquePK(estoque.getDepositoId(), estoque.getProdutoId()));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por atualizar o estoque")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_ESTOQUE" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarEstoque model, @PathVariable EstoquePK id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de estoque", result);
        }

        Estoque estoque = service.findById(id);

        if (estoque == null) {
            throw new NoResultException("Não existe estoque cadastrado com os dados inseridos.");
        }

        estoque = prepareEstoque(model, estoque);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Estoque alterado com sucesso");
        map.put("id",new EstoquePK(estoque.getDepositoId(), estoque.getProdutoId()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar todas os estoques", response = Estoque.class, notes = "Retorna todos os estoques cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Estoque> getAll() {
        return new HashSet<Estoque>(service.findAll());
    }

    @ApiOperation(value = "Buscar estoque pelo ID", response = Estoque.class, notes = "Retorna o estoque a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Estoque getById(@PathVariable EstoquePK id) {
        return service.findById(id);
    }

    private Estoque prepareEstoque(CadastrarEstoque model, Estoque estoque) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, estoque, getNullPropertyNames(model));

        estoque = service.save(estoque);
        return estoque;
    }
}
