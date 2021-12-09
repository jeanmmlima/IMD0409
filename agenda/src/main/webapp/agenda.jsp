<%@page import="com.aulas.model.ContatoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
</head>
<body>

<h1>Agenda de Contatos</h1>
<a href="novoContato.html"><button>+ Novo contato</button></a>

<%
ArrayList<ContatoBean> contatos = 
(ArrayList<ContatoBean>) request.getAttribute("contatos");
%>

<table>
	<thead>
	<tr>
		<th>Id</th>
		<th>Nome</th>
		<th>Telefone</th>
		<th>Email</th>
	</tr>
	</thead>
	
	<tbody>
	
	<% for(ContatoBean contato : contatos) {%>
		<tr>
			<td><%=contato.getId() %></td>
			<td><%=contato.getNome() %></td>
			<td><%=contato.getTelefone() %></td>
			<td><%=contato.getEmail() %></td>
			<td>
			<a href="selecionacontato?id=<%=contato.getId() %>"><button>Editar</button></a>
			<a href="javascript: confirmar(<%=contato.getId() %>)"><button>Deletar</button></a>
			</td>
		</tr>
	<% } %>	
		
	
	</tbody>
	
</table>
<script src="scripts/confirmar.js"></script>


</body>
</html>