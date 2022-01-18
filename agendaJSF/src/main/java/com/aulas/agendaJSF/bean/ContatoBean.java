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
	
	public String remover(Contato contato) throws ContatoException {
		iniciar();
		contatoDAO.remover(contato);
		return "listaContato";
	}
	
	public String novo() {
		this.contato = new Contato();
		return "formContato";
	}
	
	public String editar(Contato contato) {
		this.contato = contato;
		return "formContato";
	}
	
	public String criarOuAtualizar() throws ContatoException {
		iniciar();
		if(this.contato.getId() == null) {
			contatoDAO.adicionar(this.contato);
		} else {
			contatoDAO.atualizar(this.contato);
		}
		return "listaContato";
	}
	
	

}
