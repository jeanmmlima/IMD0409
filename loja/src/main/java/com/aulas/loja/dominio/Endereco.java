package com.aulas.loja.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable //firma a ligação dos atributos da classe endereco a uma outra classe (Clientes) que é uma tabela
public class Endereco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Relacionamento 1 -> 1
	
	@OneToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	@OneToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;
	
	@Column(length = 10)
	private String cep;
	
	@Column(length = 40)
	private String rua;
	public Endereco(Cidade cidade, Estado estado, String cep, String rua) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.rua = rua;
	}
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cep, cidade, estado, rua);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(estado, other.estado) && Objects.equals(rua, other.rua);
	}
	
	
	
}
