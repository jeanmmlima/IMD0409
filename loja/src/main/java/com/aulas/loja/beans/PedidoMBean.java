package com.aulas.loja.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.aulas.loja.dao.ClienteDAO;
import com.aulas.loja.dao.PedidoDAO;
import com.aulas.loja.dao.ProdutoDAO;
import com.aulas.loja.dominio.Cliente;
import com.aulas.loja.dominio.ItensPedido;
import com.aulas.loja.dominio.Pedido;
import com.aulas.loja.dominio.Produto;
import com.aulas.loja.util.LojaException;

@ManagedBean
@ViewScoped
public class PedidoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4379149671555061374L;

	private Pedido pedido;
	private Cliente cliente;
	private ItensPedido itensPedido;
	private Produto produto;

	private Integer pedidoIdNovo = null;
	private Date data = new Date();
	private Integer clienteId;
	private Double valorTotal = 0.0;
	private List<ItensPedido> listaItens = new ArrayList<>();
	private Integer produtoId;
	private Double produtoQtd;
	private Double produtoValor;

	// dao

	private PedidoDAO pedidoDAO = new PedidoDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ItensPedido getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(ItensPedido itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getPedidoIdNovo() {
		return pedidoIdNovo;
	}

	public void setPedidoIdNovo(Integer pedidoIdNovo) {
		this.pedidoIdNovo = pedidoIdNovo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItensPedido> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItensPedido> listaItens) {
		this.listaItens = listaItens;
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

	public Double getProdutoValor() {
		return produtoValor;
	}

	public void setProdutoValor(Double produtoValor) {
		this.produtoValor = produtoValor;
	}

	// ações - regras de negócios

	public List<Pedido> getLista() throws LojaException {
		return pedidoDAO.listar();
	}

	public List<Cliente> getListaClientes() throws LojaException {
		return clienteDAO.listar();
	}

	public List<Produto> getListaProdutos() throws LojaException {
		return produtoDAO.listar();
	}

	public void apagar(Pedido pedido) throws LojaException {
		pedidoDAO.excluir(pedido);
		retornoEstoque(pedido);
	}

	public String gravar() throws LojaException {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (cliente.getNome() != null) {
			if (!this.listaItens.isEmpty()) {

				pedido.setData(this.data);
				pedido.setCliente(this.cliente);
				pedido.setItensPedido(new HashSet<>(this.listaItens));
				pedido.setValorTotal(this.valorTotal);

				pedidoDAO.salvar(pedido);
				baixaEstoque(pedido);

				pedido = new Pedido();
				cliente = new Cliente();
				produto = new Produto();
				itensPedido = new ItensPedido();

				this.listaItens = new ArrayList<>();

				return "/pedido/pedidoLista?faces-redirect=true";

			} else {
				facesContext.addMessage("Lista de Produtos",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lista de produtos está vazia!", ""));
				return "";
			}

		} else {
			facesContext.addMessage("Clientes",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não podemos processar pedido sem cliente", ""));
			return "";
		}

	}

	public void adicionarProdutosNaLista() throws LojaException {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		Produto produtoSelecionado = produtoDAO.buscarId(this.produtoId);
		this.produtoValor = produtoSelecionado.getValor();
		Double produtoQtdEstoque = produtoSelecionado.getQuantidade();

		this.valorTotal = this.valorTotal + (this.produtoValor * this.produtoQtd);

		if (produtoQtdEstoque >= this.produtoQtd) {

			itensPedido = new ItensPedido(produtoSelecionado, produtoQtd, produtoValor);

			this.listaItens.add(itensPedido);

			this.produtoId = null;
			this.produtoQtd = null;
			this.produtoValor = null;

		} else {
			facesContext.addMessage("Erro Pedido", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"A quantidade de produto em etoque é menor que a solicitada!", ""));
		}
	}

	public void removerProdutosDaLista(ItensPedido item) {
		this.valorTotal = this.valorTotal - (item.getQuantidade() * item.getValor());
		this.listaItens.remove(item);
	}

	public void baixaEstoque(Pedido pedido) throws LojaException {
		this.listaItens = new ArrayList<>(pedido.getItensPedido());

		for (ItensPedido itensPedido : listaItens) {
			Integer idProduto = itensPedido.getProduto().getId();
			Double qtdProduto = itensPedido.getQuantidade();

			Produto produtoNovo = produtoDAO.buscarId(idProduto);
			produtoNovo.setQuantidade(produtoNovo.getQuantidade() - qtdProduto);
			produtoDAO.atualizar(produtoNovo);
		}
	}

	public void retornoEstoque(Pedido pedido) throws LojaException {
		this.listaItens = new ArrayList<>(pedido.getItensPedido());

		for (ItensPedido itensPedido : listaItens) {
			Integer idProduto = itensPedido.getProduto().getId();
			Double qtdProduto = itensPedido.getQuantidade();

			Produto produtoNovo = produtoDAO.buscarId(idProduto);
			produtoNovo.setQuantidade(produtoNovo.getQuantidade() + qtdProduto);
			produtoDAO.atualizar(produtoNovo);
		}
	}
	
	public void carregar() throws LojaException {
		
		if(this.pedidoIdNovo != null) {
			this.pedido = pedidoDAO.buscarId(this.pedidoIdNovo);
			
			this.data = this.pedido.getData();
			this.clienteId = this.pedido.getCliente().getId();
			this.cliente = clienteDAO.buscarId(clienteId);
			this.listaItens = new ArrayList<>(this.pedido.getItensPedido());
			this.valorTotal = this.pedido.getValorTotal();
		} else {
			pedido = new Pedido();
			cliente = new Cliente();
			produto = new Produto();
			itensPedido = new ItensPedido();
			
			this.data = new Date();
			this.valorTotal = 0.0;
			this.listaItens = new ArrayList<>();
		}
		
	}
	
	public void atualizaCliente(AjaxBehaviorEvent e) throws LojaException {
		cliente = clienteDAO.buscarId(clienteId);
	}
	
	public void atualizaProduto(AjaxBehaviorEvent e) throws LojaException {
		produto = produtoDAO.buscarId(produtoId);
	}
	

}
