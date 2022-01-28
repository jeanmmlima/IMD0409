package com.aulas.loja.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.aulas.loja.dao.CidadeDAO;
import com.aulas.loja.dao.ClienteDAO;
import com.aulas.loja.dao.EstadoDAO;
import com.aulas.loja.dominio.Cliente;
import com.aulas.loja.dominio.Endereco;
import com.aulas.loja.util.LojaException;

@ManagedBean
@ViewScoped
public class ClienteMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083306125873348766L;
	
	private Cliente cliente = new Cliente();
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	//Para endereço
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private EstadoDAO estadoDAO = new EstadoDAO();
	
	private Endereco endereco = new Endereco();
	
	private Integer cidadeId;
	private Integer estadoId;
	private String rua;
	private String cep;
	
	private Integer clienteId;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	
	//Ações
	
	public void apagar(Cliente cliente) throws LojaException {
		clienteDAO.excluir(cliente);
	}
	
	public List<Cliente> getLista() throws LojaException{
		return clienteDAO.listar();
	}
	
	public void gravar() throws LojaException {
		endereco = new Endereco(cidadeDAO.buscarId(cidadeId), estadoDAO.buscarId(estadoId), this.cep, this.rua);
		this.cliente.setEndereco(endereco);
		
		if(this.cliente.getId() == null) {
			clienteDAO.salvar(this.cliente);
		} else {
			clienteDAO.atualizar(this.cliente);
		}
		cliente = new Cliente();
	}
	
	
	public void editar(Cliente cliente) {
		this.cliente = cliente;
		
		//endereco
		
		this.rua = cliente.getEndereco().getRua();
		this.cep = cliente.getEndereco().getCep();
		this.cidadeId = cliente.getEndereco().getCidade().getId();
		this.estadoId = cliente.getEndereco().getEstado().getId();
	}
	
	
	
	
	
}
