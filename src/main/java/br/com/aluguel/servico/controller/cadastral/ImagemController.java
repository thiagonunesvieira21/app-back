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

import br.com.aluguel.entity.cadastral.Atributo;
import br.com.aluguel.entity.cadastral.Imagem;
import br.com.aluguel.exceptions.InvalidRequestException;
import br.com.aluguel.json.bean.cadastral.ImagemProduto;
import br.com.aluguel.servico.controller.UtilController;
import br.com.aluguel.servico.security.SecurityUser;
import br.com.aluguel.servico.service.cadastral.ImagemService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thiago on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/imagem")
public class ImagemController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ImagemService service;
    
    @ApiOperation(value = "Serviço responsável por atualizar a imagem do produto")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_IMAGEM" })
    public ResponseEntity<?> update(@RequestBody @Valid ImagemProduto model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de imagem", result);
        }

        Imagem imagem = service.findById(id);

        if (imagem == null) {
            throw new NoResultException("Não existe imagem cadastrada com os dados inseridos.");
        }

        imagem = prepareImagem(model, imagem);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Imagem alterado com sucesso");
        map.put("id", imagem.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar imagem pelo ID", response = Imagem.class, notes = "Retorna a imagem a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Imagem getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Deleta imagem pelo ID", response = Atributo.class, notes = "Exclui a imagem a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void remover(@PathVariable Integer id) {
        service.delete(id);
    }

    @ApiOperation(value = "Buscar imagens pelo ID do produto", response = Imagem.class, notes = "Retorna todas as imagens a partir do ID do produto especificado", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/produto/{idProduto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Imagem> getByIdProduto(@PathVariable Integer idProduto) {
        return new HashSet<Imagem>(service.findByProduto(idProduto));
    }

    private Imagem prepareImagem(ImagemProduto model, Imagem imagem) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, imagem, getNullPropertyNames(model));

        imagem = service.save(imagem);
        return imagem;
    }
}
