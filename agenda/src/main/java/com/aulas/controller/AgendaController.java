package com.aulas.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.RequestContext;

import com.aulas.dao.ContatoDAO;
import com.aulas.model.ContatoBean;
import com.aulas.utils.ConnectionFactory;

/**
 * Servlet implementation class AgendaController
 */
@WebServlet(urlPatterns = { "/AgendaController", "/home", "/novocontato", "/selecionacontato", "/editacontato", "/deletar" })
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgendaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String acao = request.getServletPath();
		System.out.println(acao);

		if (acao.equals("/home")) {
			contatos(request, response);
		} else if (acao.equals("/novocontato")) {
			novoContato(request, response);
		} else if (acao.equals("/selecionacontato")) {
			selecionaContato(request, response);
		} else if (acao.equals("/editacontato")) {
			editaContato(request, response);
		} else if (acao.equals("/deletar")) {
			deletaContato(request, response);
		}else {
			response.sendRedirect("index.html");
		}

		// ConnectionFactory cf = new ConnectionFactory();
		// cf.testeConnection();
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.sendRedirect("agenda.jsp");
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		ArrayList<ContatoBean> contatos = 
				contatoDAO.getContatos();
		
		contatoDAO.fechar();
		
		//encaminhar para JSP
		
		request.setAttribute("contatos", contatos);
		
		//despachante vai levar os dados para uma jsp de maneira dinamica
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response);
			

	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ContatoBean contato = new ContatoBean();
		
		//1. recuperando parametros do formulario
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.adiciona(contato);
		
		System.out.println("Contato foi gravado!");
		
		contatoDAO.fechar();

		response.sendRedirect("home");

	}
	
	protected void selecionaContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ContatoBean contato = new ContatoBean();
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contato = contatoDAO.getContato(id);
		
		contatoDAO.fechar();
		
		System.out.println(contato.getNome());
		
		request.setAttribute("contato", contato);
		
		RequestDispatcher rd = request.getRequestDispatcher("editarContato.jsp");
		
		rd.forward(request, response);
		
	} 
	
	protected void editaContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ContatoBean contato = new ContatoBean();
		
		int id = Integer.parseInt(request.getParameter("id"));
		contato.setId(id);
		
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.atualiza(contato);
		contatoDAO.fechar();
		
		response.sendRedirect("home");
		
	}
	
	protected void deletaContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ContatoBean contato = new ContatoBean();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		contato.setId(id);
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.remove(contato);
		
		contatoDAO.fechar();
		
		response.sendRedirect("home");
		
	}

}
