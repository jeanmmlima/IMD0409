package com.aulas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.aulas.bd.ConnectionFactory;
import com.aulas.model.Contato;

public class ContatoDAO {
	
	private Connection conn;
	
	public ContatoDAO() {
		
		ConnectionFactory cf = new ConnectionFactory();
		this.conn = cf.getConnection();
	}
	
	public void adiciona(Contato contato) {
		
		//string de insercao sql
		
		String sql = "insert into contatos" +
				"(nome,email,endereco,dataNascimento)" +
				" values(?,?,?,?)";
		
		try {
			//preparar a sentença
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			//dados estão no objeto contato
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			
			
			stmt.setDate(4, 
					new java.sql.Date(contato
							.getDataNascimento().getTime()));
			
			//executar sentença
			stmt.execute();
			stmt.close();
			
			//System.out.println("Contato foi gravado!");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);

		}
		
		
		
	}
	
	public ArrayList<Contato> getContatos(){
		
		String sql = "select * from contatos";
		
		
		try {
			ArrayList<Contato> contatos = 
					new ArrayList<Contato>();
			
			PreparedStatement stmt = 
					this.conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				//para cada elemento em rs, criar um contato
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				Date data = new Date();
				data.setTime(rs.getDate("dataNascimento").getTime());
				
				contato.setDataNascimento(data);
				
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
	
	public void altera(Contato contato) {
		
		String sql = "update contatos set nome=?, email=?,"+
		" endereco=?, dataNascimento=? where id=?";
		
		try {
			PreparedStatement stmt = 
					this.conn.prepareStatement(sql);
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, 
					new java.sql.Date(contato.getDataNascimento().getTime()));
			
			stmt.setInt(5, contato.getId());
			
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
	
	

}
