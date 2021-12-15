<%@page import="com.aulas.model.Contato"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
Link para DOWNLOAD biblioteca JSTL
https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 
-->    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
</head>
<body>



<h1>Agenda de Contatos</h1>
<a href="novoContato.html"><button>+ Novo contato</button></a>
<a href="relatorio"><button>Gerar Relatório</button></a>

<%
 ArrayList<Contato> contatos = 
 (ArrayList<Contato>) request.getAttribute("contatos");
%>

<h3> Tabela via scriptlet </h3>

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
		<% for(Contato contato : contatos) { %>
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

<h3>Tabela via JSTL com Expression Language (EL)</h3>

<c:set var="contatosJSTL" value="${requestScope.contatos}"/>

<c:if test="${contatosJSTL == null}">
<p> Nenhum contato foi encontrado!</p>
</c:if>

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
	
	<c:forEach var="contato" items="${contatosJSTL}" >
		<tr>
			<td>${contato.id}</td>
			<td>${contato.nome}</td>
			<td>${contato.telefone}</td>
			<td>${contato.email}</td>
			
			<td>
			<a href="selecionacontato?id=${contato.id}"><button>Editar</button></a>
			<a href="javascript: confirmar(${contato.id})"><button>Deletar</button></a>
			</td>
		</tr>
	</c:forEach>
		
	
	</tbody>
	
</table>


<script src="scripts/confirmar.js"></script>


</body>
</html>