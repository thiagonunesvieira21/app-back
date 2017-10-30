package br.com.servico.controller.cadastral;

import br.com.entity.cadastral.PrecoProduto;
import br.com.entity.cadastral.PrecoProdutoPK;
import br.com.entity.cadastral.Produto;
import br.com.exceptions.InvalidRequestException;
import br.com.json.bean.cadastral.CadastrarPrecoProduto;
import br.com.servico.controller.UtilController;
import br.com.servico.security.SecurityUser;
import br.com.servico.service.cadastral.PrecoProdutoService;
import br.com.servico.service.cadastral.ProdutoService;
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

