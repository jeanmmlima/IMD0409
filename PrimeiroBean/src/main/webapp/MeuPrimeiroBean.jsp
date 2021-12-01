<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meu Primeiro Bean</title>
</head>
<body>

<jsp:useBean id="primeirojb" 
class="com.aulas.bean.MeuPrimeiroBean" />

<p>Usando scriplet JSP</p>
A mensagem atual é: <strong><%= primeirojb.getMsg() %></strong>

<p>Usando Expression Language (EL)</p>
A mensagem atual é: <strong>${primeirojb.getMsg()} </strong>

<%--
	primeirojb.setMsg("Meu Primeiro JavaBean");
--%>

${primeirojb.setMsg("Teste modificao via EL")}

<p>A mensagem modificada é: <strong>${primeirojb.getMsg()}</strong>




</body>
</html>