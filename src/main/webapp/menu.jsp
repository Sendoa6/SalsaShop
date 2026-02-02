<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="artupa.beans.Libro" %>
<%
    String user = (String)session.getAttribute("user");
    List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SalsaShop - Catálogo</title>
    <style>
        /* Estilos simplificados para probar */
        body { background-color: #f6dcf0; font-family: Arial; margin: 0; }
        .content { display: flex; gap: 20px; padding: 20px; }
        .main { background: white; padding: 20px; border-radius: 8px; flex: 2; }
        .post { border: 1px solid #ddd; padding: 15px; border-radius: 8px; }
        .tabla-libros { width: 100%; border-collapse: collapse; }
        .tabla-libros th { border-bottom: 2px solid #B9372A; text-align: left; padding: 10px; }
        .tabla-libros td { padding: 10px; border-bottom: 1px solid #eee; }
    </style>
</head>
<body>
    <div class="content">
        <div class="main">
            <div class="post">
                <h2>Usuario: <%= (user != null) ? user : "Invitado" %></h2>
                <table class="tabla-libros">
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (listaLibros != null) { 
                            for (Libro l : listaLibros) { %>
                            <tr>
                                <td><%= l.getTitulo() %></td>
                                <td><%= l.getPrecio() %> €</td>
                            </tr>
                        <% } 
                        } else { %>
                            <tr><td colspan="2">No hay libros cargados.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>