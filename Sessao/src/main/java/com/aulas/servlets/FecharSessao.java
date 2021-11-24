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
 * Servlet implementation class FecharSessao
 */
@WebServlet("/FecharSessao")
public class FecharSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FecharSessao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sessao = request.getSession();
		
		//remove atributo da sessao para encerrá-la
		//sessao.removeAttribute("nome");
		
		sessao.invalidate();
		
		String html = "<html><head>" +
				"<title> Fechando Sessao </title></head>" +
				"<body><h2>Sessao finalizada com sucesso!</h2>"+
				"Sua ID é: <strong>" + sessao.getId() + "</strong><br />" +
				"<a href=\"CriaSessao\"> Clique aqui para criar uma nova sessão </a>" ;
				
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
