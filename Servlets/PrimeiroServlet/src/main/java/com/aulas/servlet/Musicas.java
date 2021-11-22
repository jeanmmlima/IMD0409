package com.aulas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Musicas
 */
@WebServlet("/Musicas")
public class Musicas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Musicas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] musicas = request.getParameterValues("musica");
		
		String html = "<html><head>" +
				"<title>Músicas</title></head>" +
				"<body><h2>Músicas Escolhidas</h2>";
		
		for(int i = 0; i < musicas.length; i++) {
			html += "<strong>"+musicas[i]+"</strong><br />";
		}
		
		html += "</body></html>";
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		out.print(html);
		out.close();
		 
	}

}
