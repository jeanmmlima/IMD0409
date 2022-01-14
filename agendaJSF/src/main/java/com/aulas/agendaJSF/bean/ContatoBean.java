package com.aulas.agendaJSF.bean;

import java.util.ArrayList;

import com.aulas.agendaJSF.dao.ContatoDAO;
import com.aulas.agendaJSF.model.Contato;
import com.aulas.agendaJSF.util.ContatoException;

public class ContatoBean {
	
	private Contato contato = new Contato();
	private ContatoDAO contatoDAO;
	
	public void iniciar() throws ContatoException {
		this.contatoDAO = new ContatoDAO();
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public ArrayList<Contato> getTodos() throws ContatoException{
		iniciar();
		return contatoDAO.listarContatos();
	}
	

}
