package br.com.aluguel.servico.controller.cadastral;

import java.util.HashMap;

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

import br.com.aluguel.entity.cadastral.PrecoProduto;
import br.com.aluguel.entity.cadastral.PrecoProdutoPK;
import br.com.aluguel.entity.cadastral.Produto;
import br.com.aluguel.json.bean.cadastral.CadastrarPrecoProduto;
import br.com.aluguel.servico.service.cadastral.PrecoProdutoService;
import br.com.aluguel.servico.service.cadastral.ProdutoService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 11/07/17.
 */
@RestController
@RequestMapping(value = "/api/precoProduto")
public class PrecoProdutoController extends UtilController {
    @Autowired
    private HttpSession session;

    @Autowired
    private PrecoProdutoService service;

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Serviço responsável por cadastrar o preço do produto")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_PRECO_PRODUTO" })
    public ResponseEntity<?> create(@RequestBody @Valid CadastrarPrecoProduto model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de preço do produto", result);
        }

        Produto produto = produtoService.findById(model.getProdutoId());

        if (produto==null) {
            throw new NoResultException("Não existe produto cadastrado com os dados inseridos.");
        }

        PrecoProduto precoProduto = new PrecoProduto();
        precoProduto = preparePrecoProduto(model, precoProduto);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Preço do produto cadastrado com sucesso");
        map.put("id", precoProduto);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletar preço produto pelo ID", response = Produto.class, notes = "Deleta o preço do produto a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getById(@PathVariable PrecoProdutoPK id) {
        service.delete(id);
    }

    private PrecoProduto preparePrecoProduto(CadastrarPrecoProduto model, PrecoProduto precoProduto) {
        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, precoProduto, getNullPropertyNames(model));

        precoProduto = service.save(precoProduto);
        return precoProduto;
    }
}

