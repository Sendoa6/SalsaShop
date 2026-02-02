<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="artupa.beans.Libro" %>
<%
    // Recuperamos el carrito de la sesión
    ArrayList<Libro> carrito = (ArrayList<Libro>) session.getAttribute("carrito");
    double total = 0;
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tu Carrito - SalsaShop</title>
    <style>
        body { background-color: #f6dcf0; font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        h2 { color: #B9372A; text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th { background: #fdf2f1; color: #B9372A; padding: 10px; text-align: left; }
        td { padding: 10px; border-bottom: 1px solid #eee; }
        .total { text-align: right; font-size: 1.5em; font-weight: bold; margin: 20px 0; color: #333; }
        .btn { padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold; }
        .btn-pagar { background-color: #28a745; color: white; border: none; font-size: 1.2em; cursor: pointer; }
        .btn-volver { background-color: #6c757d; color: white; }
        .btn-eliminar { color: red; font-size: 0.9em; text-decoration: underline; cursor: pointer;}
        .alert { padding: 15px; background-color: #d4edda; color: #155724; border-radius: 5px; margin-bottom: 20px; text-align: center;}
        .alert-error { background-color: #f8d7da; color: #721c24; }
    </style>
</head>
<body>

<div class="container">
    <h2>🛒 Tu Cesta de la Compra</h2>

    <% if ("exito".equals(mensaje)) { %>
        <div class="alert">¡Compra realizada con éxito! Gracias por tu confianza.</div>
    <% } else if ("error".equals(mensaje)) { %>
        <div class="alert alert-error">Hubo un error al procesar la compra. Inténtalo de nuevo.</div>
    <% } %>

    <% if (carrito == null || carrito.isEmpty()) { %>
        <p style="text-align: center; font-size: 1.2em; color: #666;">Tu carrito está vacío.</p>
        <div style="text-align: center;">
            <a href="SrvIndex" class="btn btn-volver">Volver al Catálogo</a>
        </div>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>Libro</th>
                    <th>ISBN</th>
                    <th>Precio</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <% for(Libro l : carrito) { 
                    total += l.getPrecio();
                %>
                <tr>
                    <td><%= l.getTitulo() %></td>
                    <td><%= l.getIsbn() %></td>
                    <td><%= l.getPrecio() %> €</td>
                    <td><a href="SrvCarrito?accion=eliminar&isbn=<%= l.getIsbn() %>" class="btn-eliminar">Quitar</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="total">
            Total a Pagar: <%= String.format("%.2f", total) %> €
        </div>

        <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="SrvIndex" class="btn btn-volver">Seguir Comprando</a>
            
            <form action="SrvCarrito" method="post">
                <input type="hidden" name="accion" value="pagar">
                <button type="submit" class="btn btn-pagar">✅ Pagar Ahora</button>
            </form>
        </div>
    <% } %>
</div>

</body>
</html>