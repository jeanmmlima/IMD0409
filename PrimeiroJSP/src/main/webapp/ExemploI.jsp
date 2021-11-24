<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Selecione os programas que você usa</title>
</head>
<body>

<form action="ExemploI.jsp" method="post">
	<select name="programas" size="4" multiple="multiple">
		<option value="Eclipse">Eclipse</option>
		<option value="Netbeans">NetBeans</option>
		<option value="VSCode">VSCode</option>
		<option value="CodeBlocks">CodeBlocks</option>
	</select>
	<input type="submit" name="btnEnviar" value="Enviar" />
</form>

<%

String[] linguagem = request.getParameterValues("programas");

if(linguagem != null){
	out.println("Você selecionou os seguintes programas: ");
	
	for(int i = 0; i < linguagem.length; i++){
		out.println(linguagem[i]);
	}
}



%>

</body>
</html>