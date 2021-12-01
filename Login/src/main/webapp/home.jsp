<%@page import="com.aulas.bean.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>

<p>Exibindo dados da sessao</p>

<%
	UsuarioBean usuario = 
	(UsuarioBean) session.getAttribute("usr");
%>

<p> Nome: <strong><%= usuario.getNome() %></strong>

<p> Nome: <strong>${usr.getNome()}</strong></p>
<p> Senha: <strong>${usr.getSenha()}</strong></p>




</body>
</html>