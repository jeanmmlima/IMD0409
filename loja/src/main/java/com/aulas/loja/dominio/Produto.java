package com.aulas.loja.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="produto")
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String descricao;
	
	@Column(precision = 10, scale = 2)
	private Double quantidade;
	
	@Column(precision = 10, scale = 2)
	private Double valor;
	public Produto(String descricao, Double quantidade, Double valor) {
		super();
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	public Produto(Double quantidade, Double valor) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
	}
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quatidade) {
		this.quantidade = quatidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	
	
	

}
