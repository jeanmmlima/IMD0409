package com.aulas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aulas.utils.ConnectionFactory;

/**
 * Servlet implementation class AgendaController
 */
@WebServlet(urlPatterns = {"/AgendaController","/home"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String acao = request.getServletPath();
		System.out.println(acao);
		
		if(acao.equals("/home")) {
			contatos(request,response);
		}
		
		
		
		//ConnectionFactory cf = new ConnectionFactory();
		//cf.testeConnection();
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("agenda.jsp");
		
	}

}