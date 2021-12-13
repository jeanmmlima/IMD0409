package negocio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.aulas.dao.ContatoDAO;
import com.aulas.model.ContatoBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Relatorio {

	public void geraRelatorio(HttpServletResponse response) {
		Document relatorio = new Document();

		try {

			// definir o tipo de conteudo da resposta

			response.setContentType("application/pdf");

			// nome do relatorio

			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");

			// criando o relatorio

			PdfWriter.getInstance(relatorio, response.getOutputStream());

			// Abrir o documento

			relatorio.open();

			relatorio.add(new Paragraph("Lista de Contatos"));
			relatorio.add(new Paragraph(" "));

			// PdfPTable(numero de colunas)
			PdfPTable tabela = new PdfPTable(3);

			// cabecalho

			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));

			// adicionando celulas a tabela

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			// popular tabela com dados de contato

			ArrayList<ContatoBean> contatos = new ArrayList<ContatoBean>();

			ContatoDAO contatoDAO = new ContatoDAO();

			contatos = contatoDAO.getContatos();

			contatoDAO.fechar();

			for (ContatoBean contato : contatos) {
				tabela.addCell(contato.getNome());
				tabela.addCell(contato.getTelefone());
				tabela.addCell(contato.getEmail());
			}

			relatorio.add(tabela);

			relatorio.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			relatorio.close();
		}
	}

}
