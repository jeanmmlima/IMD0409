package com.aulas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		try {
			
			ArrayList<ContatoBean> contatos = 
					new ArrayList<ContatoBean>();
			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				ContatoBean contatoBean = new ContatoBean();
				contatoBean.setId(rs.getInt("id"));
				contatoBean.setNome(rs.getString("nome"));
				contatoBean.setTelefone(rs.getString("telefone"));
				contatoBean.setEmail(rs.getString("email"));
				
				contatos.add(contatoBean);
				
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
	
	public ContatoBean getContato(int id) {
		
		String sql = "select * from contatos where id = ?";
		
		try {
			
			ContatoBean contato = new ContatoBean();
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
	
	public void atualiza(ContatoBean contatoBean) {
		
		String sql = "update contatos set nome=?, telefone=?, "+
		"email=? where id=?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setString(1, contatoBean.getNome());
			stmt.setString(2, contatoBean.getTelefone());
			stmt.setString(3, contatoBean.getEmail());
			
			stmt.setInt(4, contatoBean.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	
	public void remove(ContatoBean contato) {
		
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
	
	

}
