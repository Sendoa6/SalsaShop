<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="artupa.beans.Libro" %>
<%
    // Recuperamos el usuario de la sesión
    String user = (String)session.getAttribute("user");
    // Recuperamos la lista que el SrvIndex puso en el request
    List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SalsaShop - Panel Principal</title>
    <style>
        body { background-color: #f6dcf0; font-family: 'Segoe UI', Arial, sans-serif; margin: 0; }
        .content { display: flex; gap: 20px; padding: 20px; justify-content: center; }
        .sidebar { flex: 1; max-width: 250px; }
        .main { flex: 2; max-width: 700px; }
        .post { background: white; padding: 25px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); margin-bottom: 20px; }
        h2 { color: #B9372A; border-bottom: 2px solid #B9372A; padding-bottom: 10px; }
        
        .tabla-libros { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .tabla-libros th { text-align: left; padding: 12px; background-color: #fdf2f1; color: #B9372A; }
        .tabla-libros td { padding: 12px; border-bottom: 1px solid #eee; }
        
        .btn-mantenimiento { background: #B9372A; color: white; border: none; padding: 12px; width: 100%; border-radius: 6px; cursor: pointer; font-weight: bold; }
        .btn-mantenimiento:hover { background: #a12f24; }
        .footer { text-align: center; padding: 20px; color: #666; font-size: 0.9em; }
    </style>
</head>
<body>

<div class="content">
    <div class="sidebar">
        <div class="post">
            <p>USUARIO: <b><%= (user != null) ? user : "Invitado" %></b></p>
            <form action="SrvClientes" method="post">
                <input type="submit" value="Mantenimiento Clientes" class="btn-mantenimiento">
            </form>
        </div>
    </div>

    <div class="main">
        <div class="post">
            <h2>📚 Catálogo de Libros</h2>
            <table class="tabla-libros">
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    if (listaLibros != null && !listaLibros.isEmpty()) { 
                        for (Libro l : listaLibros) { 
                    %>
                        <tr>
                            <td><b><%= l.getTitulo() %></b></td>
                            <td><%= (l.getNombreAutor() != null) ? l.getNombreAutor() : "ID: " + l.getIdAutor() %></td>
                            <td><%= l.getPrecio() %> €</td>
                        </tr>
                    <% 
                        } 
                    } else { 
                    %>
                        <tr>
                            <td colspan="3" style="text-align:center; padding:30px; color: #999;">
                                No hay libros cargados en el sistema.<br>
                                <small>(Verifica la conexión a la BD en SrvIndex)</small>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="footer">
    <p>&copy; 2026 ArtupaWeb - SalsaShop. Todos los derechos reservados.</p>
</div>

</body>
</html>