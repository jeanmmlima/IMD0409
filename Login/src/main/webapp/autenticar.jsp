<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autentica Usuário</title>
</head>
<body>

<jsp:useBean id="usr" scope="session"
class="com.aulas.bean.UsuarioBean" />

<p><strong>Utilizando scriptlet </strong></p>

<%
	usr.setNome(request.getParameter("nome"));
	usr.setSenha(request.getParameter("senha"));

%>

<p> Nome: <strong><%= usr.getNome() %></strong></p>
<p> Senha: <strong><%= usr.getSenha() %></strong></p>


<p>Verificando validade o usuário</p>
<%
	if(usr.autenticaUsuario()){
		out.println("Usuário autenticado!");
	} else {
		out.println("Usuário NÃO autenticado!");

	}
%>

<p><strong>Utilizando EL</strong></p>

<p> Nome: <strong>${usr.getNome()}</strong></p>
<p> Senha: <strong>${usr.getSenha()}</strong></p>

<strong>${usr.autenticaUsuario() ? "Usuário Autenticado" : "Usuário não autenticado!"}</strong>

<br />

<a href="home.jsp">Ir para home</a>

<%-- SEM O BEAN

String nome = request.getParameter("nome");
String senha = request.getParameter("senha");


if(nome.equals("Joao") && senha.equals("123456")){
	out.println("Seja bem-vindo, "+nome+"!");
} else {
	out.println("Nome ou senha inválidos");
}

<p>Nome: <strong><% nome %></strong></p>

<p>Utilizando expression language (EL) </p>

<p> Nome: <strong>${param.nome}</strong> </p>
<p> Senha: <strong>${param.senha}</strong> </p>

--%>

</body>
</html>