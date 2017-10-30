package br.com.servico.controller.cadastral;

import br.com.entity.cadastral.Categoria;
import br.com.exceptions.InvalidRequestException;
import br.com.json.bean.cadastral.CadastrarCategoria;
import br.com.servico.controller.UtilController;
import br.com.servico.security.SecurityUser;
import br.com.servico.service.cadastral.CategoriaService;
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
 * Created by thiago on 29/06/17.
 */
@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CategoriaService service;

    @ApiOperation(value = "Serviço responsável por cadastrar a categoria")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_CATEGORIA" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarCategoria model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de categoria", result);
        }

        Categoria categoria = new Categoria();
        categoria = prepareCategoria(model, categoria);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Categoria cadastrada com sucesso");
        map.put("id", categoria.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por atualizar a categoria")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_CATEGORIA" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarCategoria model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de categoria", result);
        }

        Categoria categoria = service.findById(id);

        if (categoria == null) {
            throw new NoResultException("Não existe categoria cadastrada com os dados inseridos.");
        }

        categoria = prepareCategoria(model, categoria);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Categoria alterada com sucesso");
        map.put("id", categoria.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar todas as categorias", response = Categoria.class, notes = "Retorna todas as categorias cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Categoria> getAll() {
        return new HashSet<Categoria>(service.findAll());
    }

    @ApiOperation(value = "Buscar categoria pelo ID", response = Categoria.class, notes = "Retorna a categoria a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Categoria getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private Categoria prepareCategoria(CadastrarCategoria model, Categoria categoria) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, categoria, getNullPropertyNames(model));

        categoria = service.save(categoria);
        return categoria;
    }
}
