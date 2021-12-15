package bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aulas.dao.ContatoDAO;
import com.aulas.model.Contato;

public class ContatoBean {

	private Contato contato;
	private ArrayList<Contato> contatos;
	private ContatoDAO contatoDAO;

	public void deletaContato(HttpServletRequest request, HttpServletResponse response) throws IOException {

		contato = new Contato();

		int id = Integer.parseInt(request.getParameter("id"));

		contato.setId(id);

		contatoDAO = new ContatoDAO();

		contatoDAO.remove(contato);

		contatoDAO.fechar();

		response.sendRedirect("home");
	}

	public Contato selecionaContato(int id) {

		contato = new Contato();

		contatoDAO = new ContatoDAO();

		contato = contatoDAO.getContato(id);

		contatoDAO.fechar();

		return contato;
	}

	public void editaContato(HttpServletRequest request, HttpServletResponse response) throws IOException {

		contato = new Contato();

		int id = Integer.parseInt(request.getParameter("id"));
		contato.setId(id);
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		contatoDAO = new ContatoDAO();

		contatoDAO.atualiza(contato);
		contatoDAO.fechar();

		response.sendRedirect("home");
	}

	public void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if(existeContato(request.getParameter("email"))) {
			System.out.println("Contato já existe!");
			response.sendRedirect("home");
		} else {
			contato = new Contato();

			// 1. recuperando parametros do formulario
			contato.setNome(request.getParameter("nome"));
			contato.setTelefone(request.getParameter("telefone"));
			contato.setEmail(request.getParameter("email"));

			contatoDAO = new ContatoDAO();
			contatoDAO.adiciona(contato);

			// System.out.println("Contato foi gravado!");

			contatoDAO.fechar();

			response.sendRedirect("home");
		}

		
	}

	public ArrayList<Contato> getContatos() {

		contatoDAO = new ContatoDAO();

		this.contatos = contatoDAO.getContatos();

		contatoDAO.fechar();
		
		return contatos;

	}
	
	public boolean existeContato(String email) {
		
		contato = new Contato();
		contatoDAO = new ContatoDAO();
		
		contato = contatoDAO.getContatoPeloEmail(email);
		
		if(contato != null) {
			return true;
		} else {
			return false;
		}
	}

}
