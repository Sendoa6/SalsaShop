<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="artupa.beans.Libro" %>
<%
    String user = (String)session.getAttribute("user");
    List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SalsaShop - Catálogo</title>
    <style>
        body { background-color: #f6dcf0; font-family: 'Segoe UI', Arial, sans-serif; margin: 0; }
        .container { max-width: 1000px; margin: 40px auto; padding: 0 20px; }
        .header-user { text-align: right; margin-bottom: 10px; color: #555; }
        .post { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        h2 { color: #B9372A; border-bottom: 2px solid #B9372A; padding-bottom: 10px; margin-top: 0; }
        
        .tabla-libros { width: 100%; border-collapse: collapse; margin-top: 20px; }
        .tabla-libros th { text-align: left; padding: 15px; background-color: #fdf2f1; color: #B9372A; }
        .tabla-libros td { padding: 15px; border-bottom: 1px solid #eee; vertical-align: middle; }
        
        .btn-carrito { 
            background-color: #B9372A; 
            color: white; 
            padding: 8px 12px; 
            text-decoration: none; 
            border-radius: 4px;
            font-size: 0.9em;
            font-weight: bold;
            display: inline-block;
        }
        .btn-carrito:hover { background-color: #a12f24; }
        
        .footer { text-align: center; padding: 30px; color: #666; font-size: 0.9em; }
    </style>
</head>
<body>

<div class="container">
    <div class="header-user">
        Conectado como: <b><%= (user != null) ? user : "Invitado" %></b>
    </div>

    <div class="post">
        <h2>📚 Catálogo de Libros - SalsaShop</h2>
        <table class="tabla-libros">
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Precio</th>
                    <th style="text-align: center;"></th>
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
                        <td style="text-align: center;">
                            <a href="SrvCarrito?accion=anadir&isbn=<%= l.getIsbn() %>" class="btn-carrito">
                               🛒 Añadir al Carrito
                            </a>
                        </td>
                    </tr>
                <% 
                    } 
                } else { 
                %>
                    <tr>
                        <td colspan="4" style="text-align:center; padding:50px; color: #999;">
                            No hay libros disponibles en este momento.
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="footer">
    <p>&copy; 2026 ArtupaWeb - SalsaShop. Todos los derechos reservados.</p>
</div>

</body>
</html>