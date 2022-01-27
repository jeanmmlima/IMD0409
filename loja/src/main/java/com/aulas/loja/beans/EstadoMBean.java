package com.aulas.loja.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.loja.dao.EstadoDAO;
import com.aulas.loja.dominio.Estado;
import com.aulas.loja.util.LojaException;

@ManagedBean
@ViewScoped
public class EstadoMBean {
	
	Estado estado = new Estado();
	
	EstadoDAO estadoDAO = new EstadoDAO();
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getLista() throws LojaException{
		return estadoDAO.listar();
	}
	
	public void editar(Estado estado) {
		this.estado = estado;
	}
	
	public void gravar() throws LojaException {
		if(this.estado.getId() == null) {
			estadoDAO.salvar(this.estado);
		} else {
			estadoDAO.atualizar(this.estado);
		}
	}
	
	public void apagar(Estado estado) throws LojaException {
		estadoDAO.excluir(estado);
	}

}
