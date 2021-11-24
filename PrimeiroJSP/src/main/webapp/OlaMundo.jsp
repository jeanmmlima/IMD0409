<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Primeira Página JSP</title>
</head>
<body>

	<%@ include file="cabecalho.html" %>
	
	<% out.println("Olá Mundo!"); %>
	
	<%-- Comentário --%>
	
	<%! String saudacao = "Bem-vindo, usuário!"; %>
	<br />
	<%= saudacao %>
	
	<p>Data: <%= new Date() %>
	<br><br>
	
	<%
		Date hoje = new Date();
		
		SimpleDateFormat formato = 
				new SimpleDateFormat("dd/MM/yy");
	%>
	
	A data de hoje é: <strong><%= formato.format(hoje) %></strong>
	
		<%@ include file="rodape.html" %>
	
	
	
</body>
</html>