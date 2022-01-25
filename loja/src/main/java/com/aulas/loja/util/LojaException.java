package com.aulas.loja.util;

public class LojaException extends Exception{
	
	public LojaException() {
		super();
	}
	
	public LojaException(String msg, Throwable thr) {
		super(msg,thr);
	}
	
	public LojaException(String msg) {
		super(msg);
	}
	
	public LojaException(Throwable thr) {
		super(thr);
	}

}
