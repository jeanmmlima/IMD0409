package com.aulas.agendaJSF.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FabricaJDBC {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	//private String driver = "com.mysql.jdbc.Driver";
	private static final String url = 
			"jdbc:mysql://127.0.0.1:3306/agendadev";
	
	private static final String user = "root";
	private static final String password = "password";
	
	public static Connection receberConexao() throws ContatoException {
		
		//Connection conn = null;
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			throw new ContatoException(e.getMessage());
		} 
	}
	
	// NOVOS métodos para fechar conexão
	
	public static void fecharConexao(Connection conn, Statement stmt, ResultSet rs) throws ContatoException{
		fechar(conn, stmt, rs);
	}
	
	public static void fecharConexao(Connection conn, Statement stmt) throws ContatoException{
		fechar(conn, stmt, null);
	}
	
	public static void fecharConexao(Connection conn) throws ContatoException{
		fechar(conn, null, null);
	}
	
	private static void fechar(Connection conn, Statement stmt, ResultSet rs) throws ContatoException{
		
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ContatoException(e.getMessage());
		}
	}

}
