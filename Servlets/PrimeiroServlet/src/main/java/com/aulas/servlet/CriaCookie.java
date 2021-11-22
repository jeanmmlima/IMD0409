package com.aulas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CriaCookie
 */
@WebServlet("/CriaCookie")
public class CriaCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Cookie(nomeDoCookie,valorDoCookie)
		Cookie c = new Cookie("nomeCookie","José");
		
		//tempo ativo em segundos
		c.setMaxAge(120);
		
		//comentario
		c.setComment("Nome do usuário");
		
		response.addCookie(c);
		
		String html = "<html><head>" +
				"<title>Informações obtidas do contato</title></head>" +
				"<body><h2>Seu cookie foi criado!</h2>" +
				"<a href=\"VisualizarCookie\">" +
				"Clique aqui para ver o cookie </a></body></html>";
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		out.print(html);
		out.close();
		
		
		
	}

}
