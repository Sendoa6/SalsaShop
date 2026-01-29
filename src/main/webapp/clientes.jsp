<%@page import="java.util.List"%>
<%@page import="artupa.beans.Cliente"%>
<%
	String user = (String) session.getAttribute("user");
	List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<html>
<head>
<title>Esta es la web de Artupa</title>
<script language=JavaScript>
<!--
	function ejecutarOpcion(opcion) {
		if (opcion == 'alta') {
			alert('alta');
			document.form1.modo_entrada.value = 'modo_alta';
			document.form1.action = 'SrvProcesarCliente';
			document.form1.submit();
		} else {
			if (opcion == 'modificacion') {
				alert('modificacion');
				document.form1.modo_entrada.value = 'modo_modificacion';
				document.form1.action = 'SrvProcesarCliente';
				document.form1.submit();
			} else {
				alert('baja');
				document.form1.modo_entrada.value = 'modo_baja';
				document.form1.action = 'SrvEliminarCliente';
				document.form1.submit();
			}
		}
	}
	-->
</script>
</head>
<body>
	USUARIO:
	<b><%=user%></b>
	<form name=form1 method=post>
		<table>
			<tr>
				<th>Opción</th>
				<th>DNI</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
				<th>EDAD</th>
			</tr>
			<%
				Cliente cliente = null;
				for (int i = 0; i < clientes.size(); i++) {
					cliente = clientes.get(i);
			%>
			<tr>
				<td><input type="radio" name="dni"
					value="<%=cliente.getDni()%>"></td>
				<td><%=cliente.getDni()%></td>
				<td><%=cliente.getNombre()%></td>
				<td><%=cliente.getApellido()%></td>
				<td><%=cliente.getEdad()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type=hidden name=modo_entrada> <input type=button
			value=Nuevo onclick="JavaScript:ejecutarOpcion('alta')"> <input
			type=button value=Modificar
			onclick="JavaScript:ejecutarOpcion('modificacion')"> <input
			type=button value=Eliminar
			onclick="JavaScript:ejecutarOpcion('baja')">
	</form>
</body>
</html>