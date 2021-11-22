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
 * Servlet implementation class VisualizarCookie
 */
@WebServlet("/VisualizarCookie")
public class VisualizarCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizarCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie listaCookies[] = request.getCookies();
		
		Cookie nome = null;
		
		if(listaCookies != null) {
			for(int i = 0; i < listaCookies.length; i++) {
				if(listaCookies[i].getName().equals("nomeCookie")) {
					nome = listaCookies[i];
					break;
				}
			}
		}
		
		String html = "<html><head>" +
				"<title>Informações obtidas do contato</title></head>" +
				"<body><h2>O cookie foi encontrado!</h2>" +
				"Seu cookie é: <strong>" + nome.getValue() + "</strong></body></html>";
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		out.print(html);
		out.close();
		
		
	}

}
