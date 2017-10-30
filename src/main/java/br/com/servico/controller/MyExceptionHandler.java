package br.com.servico.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.exceptions.GenericServiceException;
import br.com.exceptions.InvalidRequestException;
import br.com.exceptions.MissingFieldsFilledException;
import br.com.servico.util.ErrorResource;
import br.com.servico.util.FieldErrorResource;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	
	private Log log = LogFactory.getLog(MyExceptionHandler.class);

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
    
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultException(RuntimeException e, WebRequest request) {
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("msg", e.getMessage());
    	return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(RuntimeException e, WebRequest request) {
    	HashMap<String, String> map = new HashMap<String, String>();
    	if(e.getMessage() == null) {
    		map.put("msg", "Você não possui permissão para executar esta ação");
    	} else {
    		map.put("msg", e.getMessage());
    	}
    	return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<?> handleMailAuthenticationException(RuntimeException e, WebRequest request) {
    	HashMap<String, String> map = new HashMap<String, String>();
    	log.error("Não foi possível enviar o email", e);
		map.put("msg", "Falha no envio do email");
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MissingFieldsFilledException.class)
    public ResponseEntity<?> handleMissingFieldsFilledException(RuntimeException e, WebRequest request) {
    	HashMap<String, String> map = new HashMap<String, String>();
		map.put("msg", "Todos os Campos obrigatórios devem ser preenchidos");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(RuntimeException e, WebRequest request) {
    	log.error(e.getMessage(), e);
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("msg", "Aconteceu algum erro no servidor. Tente novamente mais tarde");
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(GenericServiceException.class)
    public ResponseEntity<?> handleGenericServiceException(RuntimeException e, WebRequest request) {
    	GenericServiceException ges = (GenericServiceException) e;
    	
    	log.error(ges.getMensagem(), ges);
    	Map<String, Object> map = ges.getErros();
    	HttpStatus status = ges.getHttpStatus()==null?HttpStatus.UNPROCESSABLE_ENTITY: ges.getHttpStatus();
		return new ResponseEntity<>(map, status);
    }
    
}