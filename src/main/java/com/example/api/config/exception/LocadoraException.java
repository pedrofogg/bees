package com.example.api.config.exception;

public class LocadoraException extends RuntimeException  {

	private static final long serialVersionUID = -4894900697550030326L;

	public LocadoraException(String mensagem) {
		super(mensagem);
	}
	
	public LocadoraException() {
		super("Ocorreu algum erro");
	}
}
