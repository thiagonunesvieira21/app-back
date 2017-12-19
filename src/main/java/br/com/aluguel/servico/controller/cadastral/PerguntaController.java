package br.com.aluguel.servico.controller.cadastral;

import java.util.Date;
import java.util.HashMap;

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

import br.com.aluguel.entity.cadastral.aluguel.Atributo;
import br.com.aluguel.entity.cadastral.aluguel.Imagem;
import br.com.aluguel.entity.cadastral.aluguel.Pergunta;
import br.com.aluguel.entity.cadastral.aluguel.PerguntaPK;
import br.com.aluguel.json.bean.cadastral.CasdastroPergunta;
import br.com.aluguel.json.bean.cadastral.RespostaPergunta;
import br.com.aluguel.servico.service.cadastral.PerguntaService;
import br.com.util.controller.UtilController;
import br.com.util.exceptions.InvalidRequestException;
import br.com.util.security.SecurityUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by thiago on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/pergunta")
public class PerguntaController extends UtilController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PerguntaService service;
    
    @ApiOperation(value = "Serviço responsável por cadastrar pergunta para o anuncio")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_PERGUNTA" })
    public ResponseEntity<?> update(@RequestBody @Valid CasdastroPergunta model, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidRequestException("Validação do cadastro de pergunta", result);
        }

        Pergunta pergunta = new Pergunta();

        pergunta = preparePergunta(model, pergunta);

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Pergunta cadastrada com sucesso");
        map.put("id", pergunta.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Serviço responsável por responder a pergunta do anuncio")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Secured({ "ROLE_MANTER_PERGUNTA" })
    public ResponseEntity<?> update(@RequestBody @Valid RespostaPergunta model, @PathVariable Integer idAnuncio, 
    		@PathVariable Integer idLocatario, @PathVariable Date dhPergunta, BindingResult result) {

		if (result.hasErrors()) {
		throw new InvalidRequestException("Validação do cadastro de resposta", result);
		}
		
		Pergunta pergunta = service.findById(new PerguntaPK(dhPergunta, idAnuncio, idLocatario));
		
		if (pergunta == null) {
			throw new NoResultException("Não existe pergunta cadastrada com os dados inseridos.");
		}

        pergunta = preparePergunta(model, pergunta);
        		
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Pergunta alterado com sucesso");
        map.put("id", pergunta.getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar pergunta pelo ID", response = Pergunta.class, notes = "Retorna a pergunta a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{idAnuncio}/{idLocatario}/dhPergunta/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Pergunta getById(@PathVariable Integer idAnuncio, @PathVariable Integer idLocatario, @PathVariable Date dhPergunta) {
        return service.findById(new PerguntaPK(dhPergunta, idAnuncio, idLocatario));
    }

    @ApiOperation(value = "Deleta pergunta pelo ID", response = Pergunta.class, notes = "Exclui a pergunta a partir do ID especificado")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/{idAnuncio}/{idLocatario}/dhPergunta/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void remover(@PathVariable Integer idAnuncio, @PathVariable Integer idLocatario, @PathVariable Date dhPergunta) {
        service.delete(new PerguntaPK(dhPergunta, idAnuncio, idLocatario));
    }

    @ApiOperation(value = "Buscar perguntas pelo ID do anuncio", response = Imagem.class, notes = "Retorna todas as perguntas a partir do ID do anuncio especificado", responseContainer = "List")
    @ApiImplicitParam(paramType = "header", name = AUTH_HEADER_NAME, value = "API Key")
    @RequestMapping(value = "/anuncio/{idAnuncio}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Pergunta> getByIdProduto(@PathVariable Integer idAnuncio, @RequestParam("page") int page, @RequestParam("size") int size) {
        return service.findByIdAnuncio(idAnuncio, page, size);
    }

    private Pergunta preparePergunta(CasdastroPergunta model, Pergunta pergunta) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, pergunta, getNullPropertyNames(model));

        Date dhPergunta = new Date(System.currentTimeMillis());
        
        pergunta.setId(new PerguntaPK(dhPergunta, model.getIdAnuncio(), model.getIdLocatario()));
        
        pergunta = service.save(pergunta);
        return pergunta;
    }
    
    private Pergunta preparePergunta(RespostaPergunta model, Pergunta pergunta) {

        SecurityUser user = getAthenticatedUser(session);

        BeanUtils.copyProperties(model, pergunta, getNullPropertyNames(model));

        Date dhRespostaPergunta = new Date(System.currentTimeMillis());
        
        pergunta.setDhRespostaPergunta(dhRespostaPergunta);
        
        pergunta = service.save(pergunta);
        return pergunta;
    }
}
