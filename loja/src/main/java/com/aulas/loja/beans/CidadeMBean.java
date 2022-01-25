package com.aulas.loja.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.loja.dao.CidadeDAO;
import com.aulas.loja.dominio.Cidade;
import com.aulas.loja.util.LojaException;

@ManagedBean
@ViewScoped
public class CidadeMBean {
	
	Cidade cidade = new Cidade();
	
	CidadeDAO cidadeDAO = new CidadeDAO();

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getLista() throws LojaException{
		return cidadeDAO.listar();
	}
	
	public void editar(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public void gravar() throws LojaException {
		if(this.cidade.getId() == null) {
			cidadeDAO.salvar(this.cidade);
		} else {
			cidadeDAO.atualizar(this.cidade);
		}
	}
	
	public void apagar(Cidade cidade) throws LojaException {
		cidadeDAO.excluir(cidade);
	}
	
	
	

}
