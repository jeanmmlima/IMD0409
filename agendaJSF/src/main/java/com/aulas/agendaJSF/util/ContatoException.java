package com.aulas.agendaJSF.util;

public class ContatoException extends Exception{
	
	public ContatoException() {
		super();
	}
	
	public ContatoException(String msg, Throwable thr) {
		super(msg,thr);
	}
	
	public ContatoException(String msg) {
		super(msg);
	}
	
	public ContatoException(Throwable thr) {
		super(thr);
	}

}
