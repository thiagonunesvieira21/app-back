package br.com.aluguel.exceptions;

public class MissingFieldsFilledException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MissingFieldsFilledException(String message) {
        super(message);
    }

	public MissingFieldsFilledException() {
		super();
	}

}