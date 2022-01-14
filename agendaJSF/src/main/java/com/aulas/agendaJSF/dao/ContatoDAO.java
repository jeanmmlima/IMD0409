package com.aulas.agendaJSF.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aulas.agendaJSF.model.Contato;
import com.aulas.agendaJSF.util.ContatoException;
import com.aulas.agendaJSF.util.FabricaJDBC;

public class ContatoDAO {
	
	private Connection conexao;

	public ContatoDAO() throws ContatoException{
		
		try {
			this.conexao = FabricaJDBC.receberConexao(); 
		} catch (Exception e) {
			// TODO: handle exception
			throw new ContatoException("Erro ao conectar com o BD: "+e.getMessage());
		}
	}

	public void adicionar(Contato contato) throws ContatoException{

		// string de insercao sql

		String sql = "insert into contatos" + "(nome,telefone,email)" + " values(?,?,?)";
		if(contato == null) {
			throw new ContatoException("Objeto Contato está nulo!");
		}
		
		PreparedStatement stmt = null;

		try {
			// preparar a sentença
			stmt = conexao.prepareStatement(sql);

			// dados estão no objeto ContatoBean
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());

			// executar sentença
			stmt.execute();
			//stmt.close();

			// System.out.println("ContatoBean foi gravado!");

		} catch (SQLException e) {
			// TODO: handle exception
			throw new ContatoException("Erro ao gravar contato: " + e); 

		} finally {
			FabricaJDBC.fecharConexao(conexao, (Statement) stmt);
		}

	}

	public ArrayList<Contato> listarContatos() throws ContatoException{

		String sql = "select * from contatos";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			ArrayList<Contato> contatos = new ArrayList<Contato>();

			stmt = conexao.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// para cada elemento em rs, criar um ContatoBean
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));

				contatos.add(contato);
			}

			return contatos;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new ContatoException("Erro ao listar contatos: " + e); 

		} finally {
			FabricaJDBC.fecharConexao(conexao, stmt, rs);
		}

	}

	public Contato umContato(Integer id) throws ContatoException {

		String sql = "select * from contatos where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Contato contato = new Contato();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (!rs.next()) {
				
				throw new ContatoException("Contato id: "+ id +" não encontrado!");

			}
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setTelefone(rs.getString("telefone"));
			contato.setEmail(rs.getString("email"));
			return contato;

		} catch (SQLException e) {
			throw new ContatoException("Erro ao listar contato: " + e); 

		} finally {
			FabricaJDBC.fecharConexao(conexao, stmt, rs);
		}
	}
	

	public void atualizar(Contato contato) throws ContatoException{

		String sql = "update contatos set nome=?, telefone=?, email=? where id=?";
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());
			stmt.setInt(4, contato.getId());
			
			stmt.execute();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new ContatoException("Erro ao atualizar contato: " + e); 

		} finally {
			FabricaJDBC.fecharConexao(conexao, stmt);
		}

	}

	public void remover(Contato contato) throws ContatoException{

		
		if(contato == null) {
			throw new ContatoException("Objeto contato está nulo!");
		}
		
		String sql = "delete from contatos where id=?";
		PreparedStatement stmt = null;

		try {

			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, contato.getId());

			stmt.execute();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new ContatoException("Erro ao remover contato: " + e); 

		} finally {
			FabricaJDBC.fecharConexao(conexao, stmt);
		}

	}

}
