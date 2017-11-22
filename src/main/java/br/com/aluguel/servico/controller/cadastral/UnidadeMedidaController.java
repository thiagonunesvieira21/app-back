package br.com.aluguel.servico.controller.cadastral;

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

import br.com.aluguel.entity.cadastral.UnidadeMedida;
import br.com.aluguel.exceptions.InvalidRequestException;
import br.com.aluguel.json.bean.cadastral.CadastrarUnidadeMedida;
import br.com.aluguel.servico.controller.UtilController;
import br.com.aluguel.servico.security.SecurityUser;
import br.com.aluguel.servico.service.cadastral.UnidadeMedidaService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thiago on 28/06/17.
 */
@RestController
@RequestMapping(value = "/api/unidadeMedida")
public class UnidadeMedidaController  extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UnidadeMedidaService service;

    @ApiOperation(value = "Serviço responsável por cadastrar a unidade de medida")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_UNIDADE_MEDIDA" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarUnidadeMedida model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de marca fabricante", result);
        }

        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida = prepareUnidadeMedida(model, unidadeMedida);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Unidade de Medida cadastrada com sucesso");
        map.put("id", unidadeMedida.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Serviço responsável por atualizar a unidade de medida")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_UNIDADE_MEDIDA" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarUnidadeMedida model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de unidade de medida", result);
        }

        UnidadeMedida unidadeMedida = service.findById(id);

        if (unidadeMedida == null) {
            throw new NoResultException("Não existe unidade de medida cadastrada com os dados inseridos.");
        }

        unidadeMedida = prepareUnidadeMedida(model, unidadeMedida);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Unidade Medida alterada com sucesso");
        map.put("id", unidadeMedida.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @ApiOperation(value = "Buscar todas as unidades de medida", response = UnidadeMedida.class, notes = "Retorna todas as unidades de medidas cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<UnidadeMedida> getAll() {
        return new HashSet<UnidadeMedida>(service.findAll());
    }

    @ApiOperation(value = "Buscar unidade de medida pelo ID", response = UnidadeMedida.class, notes = "Retorna a unidade de medida a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UnidadeMedida getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private UnidadeMedida prepareUnidadeMedida(CadastrarUnidadeMedida model, UnidadeMedida unidadeMedida) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, unidadeMedida, getNullPropertyNames(model));

        unidadeMedida = service.save(unidadeMedida);
        return unidadeMedida;
    }
}
