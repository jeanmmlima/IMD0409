package com.aulas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OlaMundo extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		Date data = new Date();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Ola Mundo </title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>Olá Mundo do Servlet!</h1>");
		
		//conteudo dinamico
		out.println("A data atual é: "+data.toString());
		
		out.println("</body>");
		out.println("</html>");
		
		
	}
	
}
