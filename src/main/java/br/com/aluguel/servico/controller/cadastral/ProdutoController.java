package br.com.aluguel.servico.controller.cadastral;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.aluguel.entity.cadastral.Atributo;
import br.com.aluguel.entity.cadastral.Imagem;
import br.com.aluguel.entity.cadastral.Produto;
import br.com.aluguel.exceptions.InvalidRequestException;
import br.com.aluguel.json.bean.cadastral.AtributoProduto;
import br.com.aluguel.json.bean.cadastral.CadastrarProduto;
import br.com.aluguel.json.bean.cadastral.ImagemProduto;
import br.com.aluguel.servico.controller.UtilController;
import br.com.aluguel.servico.security.SecurityUser;
import br.com.aluguel.servico.service.cadastral.AtributoService;
import br.com.aluguel.servico.service.cadastral.ImagemService;
import br.com.aluguel.servico.service.cadastral.ProdutoService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static br.com.aluguel.servico.util.CodigoProduto.getCodigo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by thiago on 29/06/17.
 */
@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ProdutoService service;

    @Autowired
    private AtributoService atributoService;

    @Autowired
    private ImagemService imagemService;

    @ApiOperation(value = "Serviço responsável por cadastrar o produto")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_PRODUTO" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarProduto model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de produto", result);
        }

        Produto produto = new Produto();
        produto = prepareProduto(model, produto);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Produto cadastrado com sucesso");
        map.put("id", produto.getId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por atualizar o produto")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_PRODUTO" })
    public ResponseEntity<?> update(@RequestBody @Valid CadastrarProduto model, @PathVariable Integer id,
                                    BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de produto", result);
        }

        Produto produto = service.findById(id);

        if (produto == null) {
            throw new NoResultException("Não existe produto cadastrado com os dados inseridos.");
        }

        produto = prepareAlterarProduto(model, produto);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Produto alterada com sucesso");
        map.put("id", produto.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar os produtos paginados", response = Produto.class, notes = "Retorna os produtos cadastradas no sistema", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Produto> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return service.findPaginated(page, size);
    }

    @ApiOperation(value = "Buscar produto pelo ID", response = Produto.class, notes = "Retorna o produto a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Produto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    private Produto prepareProduto(CadastrarProduto model, Produto produto) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, produto, getNullPropertyNames(model));

        produto.setCodigo(getCodigo());

        produto.setAtributos(new HashSet<>());
        produto.setImagems(new HashSet<>());

        for (AtributoProduto atributoProduto : model.getAtributos()) {
            Atributo atributo = new Atributo();

            BeanUtils.copyProperties(atributoProduto, atributo, getNullPropertyNames(atributoProduto));
            atributo.setProduto(produto);

            produto.getAtributos().add(atributo);
        }

        for (ImagemProduto imagemProduto : model.getImagems()) {
            Imagem imagem = new Imagem();

            BeanUtils.copyProperties(imagemProduto, imagem, getNullPropertyNames(imagemProduto));
            imagem.setProduto(produto);

            produto.getImagems().add(imagem);
        }

        produto = service.save(produto);
        return produto;
    }

    private Produto prepareAlterarProduto(CadastrarProduto model, Produto produto) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, produto, getNullPropertyNames(model));
        produto.setAtributos(null);
        produto.setImagems(null);

        if(model.getAtributos()!=null) {
            for (AtributoProduto atributoModel : model.getAtributos()) {
                Atributo atributo = new Atributo();

                if (atributoModel.getId() != null) {
                    atributo = atributoService.findById(atributoModel.getId());
                } else {
                    atributo.setProduto(produto);
                    atributo.setIdProduto(produto.getId());
                }

                BeanUtils.copyProperties(atributoModel, atributo, getNullPropertyNames(atributoModel));

                atributoService.save(atributo);
            }
        }

        if(model.getImagems()!=null) {
            for (ImagemProduto imagemModel : model.getImagems()) {
                Imagem imagem = new Imagem();

                if (imagemModel.getId() != null) {
                    imagem = imagemService.findById(imagemModel.getId());
                } else {
                    imagem.setProduto(produto);
                }

                BeanUtils.copyProperties(imagemModel, imagem, getNullPropertyNames(imagemModel));

                imagemService.save(imagem);
            }
        }

        produto = service.save(produto);

        return produto;
    }
}
