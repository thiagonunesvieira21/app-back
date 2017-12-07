package br.com.aluguel.servico.controller.cadastral;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguel.entity.cadastral.aluguel.Anuncio;
import br.com.aluguel.entity.cadastral.aluguel.Atributo;
import br.com.aluguel.entity.cadastral.aluguel.Imagem;
import br.com.aluguel.json.bean.cadastral.CadastroAnuncio;
import br.com.aluguel.json.bean.cadastral.AtributoAnuncio;
import br.com.aluguel.json.bean.cadastral.ImagemAnuncio;
import br.com.aluguel.servico.service.cadastral.AnuncioService;
import br.com.aluguel.servico.service.cadastral.AtributoService;
import br.com.aluguel.servico.service.cadastral.ImagemService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 29/06/17.
 */
@RestController
@RequestMapping(value = "/api/anuncio")
public class AnuncioController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AnuncioService service;

    @Autowired
    private AtributoService atributoService;

    @Autowired
    private ImagemService imagemService;

    @ApiOperation(value = "Serviço responsável por cadastrar o anuncio")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_ANUNCIO" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastroAnuncio model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de produto", result);
        }

        Anuncio anuncio = new Anuncio();
        
        anuncio = prepareAnuncio(model, anuncio);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Anuncio cadastrado com sucesso");
        map.put("id", anuncio.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por atualizar o produto")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_ANUNCIO" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastroAnuncio model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de anuncio", result);
        }

        Anuncio anuncio = service.findById(id);

        if (anuncio == null) {
            throw new NoResultException("Não existe anuncio cadastrado com os dados inseridos.");
        }

        anuncio = prepareAlterarAnuncio(model, anuncio);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Anuncio alterada com sucesso");
        map.put("id", anuncio.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar os anuncios paginados", response = Anuncio.class, notes = "Retorna os anuncios cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Anuncio> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return service.findPaginated(page, size);
    }

    @ApiOperation(value = "Buscar anuncio pelo ID", response = Anuncio.class, notes = "Retorna o anuncio a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Anuncio getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private Anuncio prepareAnuncio(CadastroAnuncio model, Anuncio anuncio) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, anuncio, getNullPropertyNames(model));

        anuncio.setAtributos(new HashSet<>());
        anuncio.setImagems(new HashSet<>());

        for (AtributoAnuncio atributoProduto : model.getAtributos()) {
            Atributo atributo = new Atributo();

            BeanUtils.copyProperties(atributoProduto, atributo, getNullPropertyNames(atributoProduto));
            atributo.setAnuncio(anuncio);

            anuncio.getAtributos().add(atributo);
        }

        for (ImagemAnuncio imagemProduto : model.getImagems()) {
            Imagem imagem = new Imagem();

            BeanUtils.copyProperties(imagemProduto, imagem, getNullPropertyNames(imagemProduto));
            imagem.setAnuncio(anuncio);

            anuncio.getImagems().add(imagem);
        }
        anuncio.setDhAnuncio(new Date(System.currentTimeMillis()));
        anuncio = service.save(anuncio);
        return anuncio;
    }

    private Anuncio prepareAlterarAnuncio(CadastroAnuncio model, Anuncio anuncio) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, anuncio, getNullPropertyNames(model));
        anuncio.setAtributos(null);
        anuncio.setImagems(null);

        if(model.getAtributos()!=null) {
            for (AtributoAnuncio atributoModel : model.getAtributos()) {
                Atributo atributo = new Atributo();

                if (atributoModel.getId() != null) {
                    atributo = atributoService.findById(atributoModel.getId());
                } else {
                    atributo.setAnuncio(anuncio);
                    atributo.setIdAnuncio(anuncio.getId());
                }

                BeanUtils.copyProperties(atributoModel, atributo, getNullPropertyNames(atributoModel));

                atributoService.save(atributo);
            }
        }

        if(model.getImagems()!=null) {
            for (ImagemAnuncio imagemModel : model.getImagems()) {
                Imagem imagem = new Imagem();

                if (imagemModel.getId() != null) {
                    imagem = imagemService.findById(imagemModel.getId());
                } 
                else {
                    imagem.setAnuncio(anuncio);
                }

                BeanUtils.copyProperties(imagemModel, imagem, getNullPropertyNames(imagemModel));

                imagemService.save(imagem);
            }
        }

        anuncio = service.save(anuncio);

        return anuncio;
    }
}
