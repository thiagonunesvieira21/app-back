package br.com.servico.controller.cadastral;

import br.com.entity.cadastral.MarcaFabricante;
import br.com.exceptions.InvalidRequestException;
import br.com.json.bean.cadastral.CadastrarMarcaFabricante;
import br.com.servico.controller.UtilController;
import br.com.servico.security.SecurityUser;
import br.com.servico.service.cadastral.MarcaFabricanteService;
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
 * Created by thiago on 21/06/17.
 */
@RestController
@RequestMapping(value = "/api/marcaFabricante")
public class MarcaFabricanteController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private MarcaFabricanteService service;

    @ApiOperation(value = "Serviço responsável por cadastrar o marca e/ou fabricantes")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_MARCA_FABRICANTE" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarMarcaFabricante model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de marca fabricante", result);
        }

        MarcaFabricante marcaFabricante = new MarcaFabricante();
        marcaFabricante = prepareMarcaFabricante(model, marcaFabricante);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Marca/Fabricante cadastrado com sucesso");
        map.put("nuMarcaFabricante", marcaFabricante.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Serviço responsável por atualizar o grupo do usuário")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_MARCA_FABRICANTE" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarMarcaFabricante model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de marca fabricante", result);
        }

        MarcaFabricante marcaFabricante = service.findById(id);

        if (marcaFabricante == null) {
            throw new NoResultException("Não existe a marca fabricante cadastrado com os dados inseridos.");
        }

        marcaFabricante = prepareMarcaFabricante(model, marcaFabricante);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Marca/Fabricante alterada com sucesso");
        map.put("nuMarcaFabricante", marcaFabricante.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @ApiOperation(value = "Buscar todas as marca/fabricante", response = MarcaFabricante.class, notes = "Retorna todos as marca/fabricante cadastrados no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<MarcaFabricante> getAll() {
        return new HashSet<MarcaFabricante>(service.findAll());
    }

    @ApiOperation(value = "Buscar marca/fabricante pelo ID", response = MarcaFabricante.class, notes = "Retorna a marca/fabricante a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MarcaFabricante getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private MarcaFabricante prepareMarcaFabricante(CadastrarMarcaFabricante model, MarcaFabricante marcaFabricante) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, marcaFabricante, getNullPropertyNames(model));

        marcaFabricante = service.save(marcaFabricante);
        return marcaFabricante;
    }
}
