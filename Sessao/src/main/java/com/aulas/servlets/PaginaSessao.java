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
 * Servlet implementation class PaginaSessao
 */
@WebServlet("/PaginaSessao")
public class PaginaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaSessao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sessao = request.getSession();
		
		String nome = (String) sessao.getAttribute("nome");
		
		String html = "<html><head>" +
				"<title> Visualizando Sessao </title></head>" +
				"<body>";
		
		if(nome != null) {
			html += "Sua ID é: <strong>" + sessao.getId() + "</strong><br />" +
					"Seu nome é: " + nome + "<br />" + 
					"<a href=\"FecharSessao\"> Clique aqui para encerrar a sessão </a>" ;
		} else {
			html += "Sua sessao não foi criada! <br />" +
					"<a href=\"CriaSessao\"> Clique aqui para criar a sessão </a>" ;
		}
				
				
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
