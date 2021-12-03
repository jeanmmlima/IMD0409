package com.aulas.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private String driver = "com.mysql.cj.jdbc.Driver";

	//127.0.0.1:3306/nome_banco
	private String url = "jdbc:mysql://localhost:3306/aulas";
	
	private String user = "root";
	
	private String password = "password";
	
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}
	
	public void testeConnection() {
		try {
			
			Connection conn = getConnection();
			System.out.println("Conexão foi aberta com sucesso!");
			System.out.println(conn);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
