package com.aulas.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aulas.dao.ContatoDAO;
import com.aulas.model.Contato;

/**
 * Servlet implementation class InserirContato
 */
@WebServlet("/inserircontato")
public class InserirContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirContato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//1. Instanciar e preencher os dados de contato
		
		Contato contato = new Contato();
		
		contato.setNome("Francisco");
		contato.setEmail("chico@imd.ufrn.br");
		contato.setEndereco("Av. Senador Salgado Filho 3000");
		
		Date data = new Date("2001/04/03");
		contato.setDataNascimento(data);
		
		//2. Instancia do objeto DAO
		//2.1 instancia de DAO já estabelece a comunicação
		ContatoDAO dao = new ContatoDAO();
		
		//2.2 método elegante de inserir dado
		dao.adiciona(contato);
		
		System.out.println("Contato foi gravado via DAO!");
		
		
		ArrayList<Contato> contatos = dao.getContatos();
		
		for(Contato contatoResultado : contatos) {
			System.out.println("Nome: "+contatoResultado.getNome());
			System.out.println("Email: "+contatoResultado.getEmail());
		}
		
		Contato contatoParaAtualizar = contatos.get(1);
		
		contatoParaAtualizar.setNome("Joao Maria");
		
		dao.altera(contatoParaAtualizar);
		
		
		Contato contatoParaRemover = contatos.get(3);
		
		dao.remove(contatoParaRemover);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
