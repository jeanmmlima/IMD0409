package com.aulas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aulas.model.ContatoBean;
import com.aulas.utils.ConnectionFactory;

public class ContatoDAO {
	
	private Connection conn;
	
	public ContatoDAO() {
		ConnectionFactory cf = new ConnectionFactory();
		this.conn = cf.getConnection();
	}
	
	public void fechar() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void adiciona(ContatoBean contatoBean) {
		
		//1. string de inserção
		
		String sql = "insert into contatos" +
					"(nome,telefone,email)" +
					"values(?,?,?)";
		
		try {
			//2. preparar a senteça
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//3. passar dados que estão no objeto contatoBean
			
			stmt.setString(1, contatoBean.getNome());
			stmt.setString(2, contatoBean.getTelefone());
			stmt.setString(3, contatoBean.getEmail());
			
			//4. executar a setença
			stmt.execute();
			stmt.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ContatoBean> getContatos(){
		
		String sql = "select * from contatos";
		
	}
	
	

}
