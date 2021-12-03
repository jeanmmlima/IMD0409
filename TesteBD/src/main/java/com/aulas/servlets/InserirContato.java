package com.aulas.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aulas.bd.ConnectionFactory;

/**
 * Servlet implementation class InserirContato
 */
@WebServlet("/inserircontato")
public class InserirContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirContato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn = null;
		try {
			
			//instancia da fabrica de conexoes
			ConnectionFactory cf = new ConnectionFactory();
			
			//conexao
			conn = cf.getConnection();
			
			//string de insercao sql
			
			String sql = "insert into contatos" +
					"(nome,email,endereco,dataNascimento)" +
					" values(?,?,?,?)";
			
			//preparar a sentença
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			//dados na sentença
			stmt.setString(1, "João");
			stmt.setString(2, "joao@imd.ufrn.br");
			stmt.setString(3, "R. Cap. Mor Gouveia");
			
			Date data = new Date("2000/01/01");
			
			stmt.setDate(4, 
					new java.sql.Date(data.getTime()));
			
			//executar sentença
			stmt.execute();
			stmt.close();
			
			System.out.println("Contato foi gravado!");
			
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
