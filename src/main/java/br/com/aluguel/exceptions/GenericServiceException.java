package br.com.aluguel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class GenericServiceException extends GenericRuntimeException{

	private static final String MSG = "msg";
	private HashMap<String, Object> erros;
	private HttpStatus httpStatus;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512867789150206208L;

	public GenericServiceException(String mensagem) {
		super(mensagem);
		HashMap<String, Object> map = new HashMap<>();
		map.put(MSG, mensagem);
		setErros(map);
	}
	public GenericServiceException(String mensagem,HttpStatus status) {
		super(mensagem);
		HashMap<String, Object> map = new HashMap<>();
		map.put(MSG, mensagem);
		setErros(map);
		setHttpStatus(status);
	}
	
	public GenericServiceException(Map<String, Object> erros) {
		this(erros.toString());
		setErros(erros);
	}
	public GenericServiceException(Map<String, Object> erros,HttpStatus status) {
		this(erros);
		setHttpStatus(status);
	}

	public GenericServiceException(String mensagem, ReflectiveOperationException e) {
		super(mensagem, e);
	}
	public GenericServiceException(String mensagem, Throwable e) {
		super(mensagem, e);
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Map<String, Object> getErros() {
		return erros;
	}

	public void setErros(Map<String, Object> erros) {
		this.erros = new HashMap<>(erros);
	}
	

}
