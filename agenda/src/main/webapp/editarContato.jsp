<%@page import="com.aulas.model.Contato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Contato</title>
</head>
<body>

<%
Contato contato = 
(Contato) request.getAttribute("contato");
%>

<h1>Editar contatao</h1>

<form name="formEditContato" action="editacontato">
	<input type="hidden" name="id" value="<%=contato.getId() %>">
	<input placeholder="Nome" type="text" name="nome" value="<%=contato.getNome() %>" required><br />
	<input placeholder="Telefone" type="text" name="telefone" value="<%=contato.getTelefone() %>" required><br />
	<input placeholder="Email" type="text" name="email" value="<%=contato.getEmail() %>" ><br />
	
	<input type="submit" value="Editar Contato">
	
</form>

</body>
</html>