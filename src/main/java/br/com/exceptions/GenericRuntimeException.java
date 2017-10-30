package br.com.exceptions;

public class GenericRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	private Throwable throwable;
	
	
	
	public GenericRuntimeException(String mensagem) {
		super();
		setMensagem(mensagem);
	}
	public GenericRuntimeException(String mensagem, Throwable throwable) {
		this(mensagem);
		setThrowable(throwable);
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	

}
