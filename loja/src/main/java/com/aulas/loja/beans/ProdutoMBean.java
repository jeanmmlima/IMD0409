package com.aulas.loja.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.loja.dao.ProdutoDAO;
import com.aulas.loja.dominio.Produto;
import com.aulas.loja.util.LojaException;

import net.bytebuddy.asm.Advice.This;

@ManagedBean
@ViewScoped
public class ProdutoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6783781356226319385L;
	
	private Produto produto = new Produto(0.0,0.0);
	private Integer produtoId;
	private Double produtoQtd;
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Double getProdutoQtd() {
		return produtoQtd;
	}

	public void setProdutoQtd(Double produtoQtd) {
		this.produtoQtd = produtoQtd;
	}
	
	//Ações
	
	public void apagar(Produto produto) throws LojaException {
		produtoDAO.excluir(produto);
	}
	
	public List<Produto> getLista() throws LojaException{
		return produtoDAO.listar();
	}
	
	public void editar(Produto produto) {
		this.produto = produto;
		this.produtoId = produto.getId();
	}
	
	public void gravar() throws LojaException {
		if(this.produto.getId() == null) {
			produtoDAO.salvar(this.produto);
		} else {
			produtoDAO.atualizar(produto);
		}
		this.produto = new Produto(0.0,0.0);
	}
	
	

}
