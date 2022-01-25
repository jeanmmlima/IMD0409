package com.aulas.loja.util;

import com.aulas.loja.dao.CidadeDAO;
import com.aulas.loja.dao.ClienteDAO;
import com.aulas.loja.dao.EstadoDAO;
import com.aulas.loja.dao.ProdutoDAO;
import com.aulas.loja.dao.UsuarioDAO;
import com.aulas.loja.dominio.Cidade;
import com.aulas.loja.dominio.Cliente;
import com.aulas.loja.dominio.Endereco;
import com.aulas.loja.dominio.Estado;
import com.aulas.loja.dominio.Produto;
import com.aulas.loja.dominio.Usuario;

public class PovoaDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dados();

	}
	
	public static void dados() {
		
		try {

			EstadoDAO estadoDAO = new EstadoDAO();
			CidadeDAO cidadeDAO = new CidadeDAO();
			ClienteDAO clienteDAO = new ClienteDAO();
			ProdutoDAO produtoDAO= new ProdutoDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			String[] arrayCidade = new String[] { "Natal", "Salvador", "João Pessoa", "Parnamirim", "Recife", "Fortaleza" };
			String[] arrayEstado = new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG",
					"MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };

			// Início da geração das tabelas
			System.out.println("Início da gravação das tabelas");

			for (int i = 0; i < arrayCidade.length; i++) {
				cidadeDAO.salvar(new Cidade(arrayCidade[i]));
			}

			System.out.println("Gerou a Tabela de Cidade");

			for (int i = 0; i < arrayEstado.length; i++) {
				estadoDAO.salvar(new Estado(arrayEstado[i]));
			}

			System.out.println("Gerou a tabela de Estado");
			
			Endereco endereco1 = new Endereco(cidadeDAO.buscarId(1), estadoDAO.buscarId(20), "59064-000", "Av. Senador Salgado Filho");
			Endereco endereco2 = new Endereco(cidadeDAO.buscarId(4), estadoDAO.buscarId(20), "59140-190", "Av. Comandante Petit");
			Endereco endereco3 = new Endereco(cidadeDAO.buscarId(2), estadoDAO.buscarId(20), "40020-025", "Av. Sá Ferreira");
			
			System.out.println("Gerou a tabela de Endereços");
			
			Cliente cliente1 = new Cliente("João Maria", "999.888.777-00", "joao.maria@imd.ufrn.br", endereco1);
			Cliente cliente2 = new Cliente("José Silva", "998.887.776-00", "jose.silva@imd.ufrn.br", endereco2);
			Cliente cliente3 = new Cliente("Maria Lima", "997.886.775-00", "maria.lima@ufba.br", endereco3);
			
			clienteDAO.salvar(cliente1);
			clienteDAO.salvar(cliente2);
			clienteDAO.salvar(cliente3);
			
			System.out.println("Clientes salvos!");
			
			Produto livro = new Produto("Livro", 10.00, 100.00);
			Produto caderno = new Produto("Caderno", 43.00, 12.80);
			Produto papel = new Produto("Folha A4", 100.00, 18.90);
			Produto tinta = new Produto("Tinta Guache", 9.50, 3.30);
			Produto borracha = new Produto("Borraca", 1000.00, 6.30);
			Produto lapis = new Produto("Lapis", 100.00, 5.50);
			
			produtoDAO.salvar(livro);
			produtoDAO.salvar(caderno);
			produtoDAO.salvar(papel);
			produtoDAO.salvar(tinta);
			produtoDAO.salvar(borracha);
			produtoDAO.salvar(lapis);
			
			System.out.println("Produtos salvos!");
			
			Usuario usuario = new Usuario("user", "123");
			
			usuarioDAO.salvar(usuario);
			
			System.out.println("Usuario salvo!");
			
			System.out.println("Finalizou a geração de tabelas!");
			
			System.exit(0);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
