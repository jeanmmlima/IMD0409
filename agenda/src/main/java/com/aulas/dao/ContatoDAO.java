package com.aulas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aulas.model.Contato;
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
	
	public void adiciona(Contato contato) {
		
		//1. string de inserção
		
		String sql = "insert into contatos" +
					"(nome,telefone,email)" +
					"values(?,?,?)";
		
		try {
			//2. preparar a senteça
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//3. passar dados que estão no objeto contatoBean
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());
			
			//4. executar a setença
			stmt.execute();
			stmt.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contato> getContatos(){
		
		String sql = "select * from contatos";
		
		try {
			
			ArrayList<Contato> contatos = 
					new ArrayList<Contato>();
			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				
				contatos.add(contato);
				
			}
			
			rs.close();
			stmt.close();
			
			return contatos;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
		
		
	}
	
	public Contato getContato(int id) {
		
		String sql = "select * from contatos where id = ?";
		
		try {
			
			Contato contato = new Contato();
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
			}
			
			rs.close();
			stmt.close();
			return contato;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	public void atualiza(Contato contato) {
		
		String sql = "update contatos set nome=?, telefone=?, "+
		"email=? where id=?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());
			
			stmt.setInt(4, contato.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	
	public void remove(Contato contato) {
		
		String sql = "delete from contatos where id=?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setInt(1, contato.getId());
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	public Contato getContatoPeloEmail(String email) {
		
		String sql = "select * from contatos where email = ?";
		Contato contato = null;
		
		try {
			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				contato = new Contato();
				
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
			}
			
			rs.close();
			stmt.close();
			
			return contato;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}
	
	

}
