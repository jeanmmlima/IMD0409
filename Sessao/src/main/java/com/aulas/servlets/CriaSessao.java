package com.aulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CriaSessao
 */
@WebServlet("/CriaSessao")
public class CriaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaSessao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//criar sessao
		HttpSession sessao = request.getSession();
		
		sessao.setAttribute("nome", "Jose");
		
		sessao.setMaxInactiveInterval(120);
		
		String html = "<html><head>" +
		"<title> Criando Sessao </title></head>" +
		"<body><h2>Sessao criada com sucesso!</h2>"+
		"Sua ID é: <strong>" + sessao.getId() + "</strong><br />" +
		"<a href=\"PaginaSessao\"> Clique aqui para ver a sessão </a>" ;
		
		html += "</body></html>";
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print(html);
		
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
