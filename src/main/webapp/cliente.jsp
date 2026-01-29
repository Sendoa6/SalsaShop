<%@page import="artupa.beans.Cliente"%>
<%
	String user = (String)session.getAttribute("user");
	String modo_entrada = request.getParameter("modo_entrada");	
	String opcion = null;	
	Cliente cliente = null;
	if (modo_entrada.equals("modo_alta"))
	{
		opcion="alta";
	}
	else
	{
		opcion="modificacion";
		cliente = (Cliente)request.getAttribute("cliente");
	}
	
%>
<html>
<head>
<title>Esta es la web de Artupa</title>
<script language=JavaScript>
<!--
function ejecutarOpcion(opcion)
{
	if (opcion == 'alta')
	{
		document.form1.modo_entrada.value='modo_alta';
		document.form1.action='SrvActualizarCliente';
		document.form1.submit();		
	}
	else 
	{
		document.form1.modo_entrada.value='modo_modificacion';
		document.form1.action='SrvActualizarCliente';
		document.form1.submit();
	}
}
-->
</script>
</head>
<body>
USUARIO: <b><%=user%></b>
<form name=form1 method=post>
<%
if (modo_entrada.equals("modo_alta"))
{
%>
DNI: <input type=text name=dni>
<br>
NOMBRE: <input type=text name=nombre size=30>
<br>
APELLIDO: <input type=text name=apellido size=30>
<br>
EDAD: <input type=text name=edad>
<br>
DIRECCIÓN: <input type=text name=direccion size=30>
<br>
CÓDIGO POSTAL: <input type=text name=codPostal>
<br>
LOCALIDAD: <input type=text name=localidad size=30>
<br>
TELÉFONO: <input type=text name=telefono size=30>
<%
}
else
{
%>
DNI: <input type=text name=dni value="<%=cliente.getDni()%>" style="color:red" readonly>
<br>
NOMBRE: <input type=text name=nombre size=30 value="<%=cliente.getNombre()%>">
<br>
APELLIDO: <input type=text name=apellido size=30 value="<%=cliente.getApellido()%>">
<br>
EDAD: <input type=text name=edad value="<%=cliente.getEdad()%>">
<br>
DIRECCIÓN: <input type=text name=direccion size=30 value="<%=cliente.getDireccion()%>">
<br>
CÓDIGO POSTAL: <input type=text name=codPostal value="<%=cliente.getCodPostal()%>">
<br>
LOCALIDAD: <input type=text name=localidad size=30 value="<%=cliente.getLocalidad()%>">
<br>
TELÉFONO: <input type=text name=telefono value="<%=cliente.getTelefono()%>">
<%
}
%>
<input type=hidden name=modo_entrada value=<%=opcion%>>
<input type=button value=Grabar onclick="JavaScript:ejecutarOpcion('<%=opcion%>')">
</form> 
</body>
</html>