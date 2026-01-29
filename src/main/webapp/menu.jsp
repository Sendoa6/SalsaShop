<%
	String user = (String)session.getAttribute("user");
%>
<html>
<head>
<title>Esta es la web de Artupa</title>
<script language=JavaScript>
<!--
-->
</script>
</head>
<body>
USUARIO: <b><%=user%></b>
<form name=form1 action=SrvClientes method=post>
<input type=submit value="Mantenimiento de Clientes">
</form> 
</body>
</html>