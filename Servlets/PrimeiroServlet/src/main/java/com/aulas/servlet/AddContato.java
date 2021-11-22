package com.aulas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddContato
 */
@WebServlet("/AddContato")
public class AddContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContato() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doPost(request,response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimento = request.getParameter("dataNascimento");
		
		String html = "<html><head>" +
		"<title>Informações obtidas do contato</title></head>" +
		"<body>" +
		"Nome: " + nome + "<br />" +
		"Email: " + email + "<br />" +
		"Endereço: " + endereco + "<br />" +
		"Data Nascimento: " + dataNascimento + "<br />" +
		"</body></html>";
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		out.print(html);
		out.close();
		
		
	}

}
