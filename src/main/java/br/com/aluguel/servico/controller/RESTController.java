package br.com.aluguel.servico.controller;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aluguel.exceptions.InvalidRequestException;
import io.swagger.annotations.ApiOperation;


public abstract class RESTController<T, ID extends Serializable> {

    private Logger logger = LoggerFactory.getLogger(RESTController.class);

    private JpaRepository<T, ID> repo;


    public RESTController(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    @RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<T> listAll() {
    	return this.repo.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> create(@RequestBody T json, BindingResult result) {
        logger.debug("create() with body {} of type {}", json, json.getClass());
        
        if (result.hasErrors()) {
			throw new InvalidRequestException("Erro de validação na entidade", result);
		}

        T created = this.repo.save(json);

        Map<String, Object> m = new HashMap<>();
        m.put("success", true);
        m.put("created", created);
        
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public @ResponseBody T get(@PathVariable ID id) {
        return this.repo.findOne(id);
    }

  @RequestMapping(value="/{id}", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody Map<String, Object> update(@PathVariable ID id, @RequestBody T json) {
	  T updated = this.repo.save(json);

      logger.debug("updated enitity: {}", updated);

      Map<String, Object> m = new HashMap<>();
      m.put("success", true);
      m.put("id", id);
      m.put("updated", updated);
      return m;
  }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(hidden=true, value = "")
    public @ResponseBody Map<String, Object> delete(@PathVariable ID id) {
        this.repo.delete(id);
        Map<String, Object> m = new HashMap<>();
        m.put("success", true);
        return m;
    }
    
}