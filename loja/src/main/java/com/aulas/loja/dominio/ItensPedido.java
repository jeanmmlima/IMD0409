package com.aulas.loja.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// N itens pedido --> 1 pedido
	@ManyToOne
	private Pedido pedido;
	
	// 1 iten --> 1 produto
	@OneToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Column(precision = 10, scale = 2)
	private Double quantidade; 
	
	@Column(precision = 10, scale = 2)
	private Double valor;
	public ItensPedido(Produto produto, Double quantidade, Double valor) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	public ItensPedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, pedido, produto, quantidade, valor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensPedido other = (ItensPedido) obj;
		return Objects.equals(id, other.id) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(produto, other.produto) && Objects.equals(quantidade, other.quantidade)
				&& Objects.equals(valor, other.valor);
	}
	
	
	
	

}
