<%@page import="java.util.ArrayList"%>
<%@page import="artupa.beans.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tu Carrito - SalsaShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">📚 SalsaShop</a> </div>
    </nav>

    <div class="container">
        <h2 class="mb-4">🛒 Tu Carrito de la Compra</h2>

        <%-- Mensajes de éxito o error --%>
        <% if (request.getParameter("mensaje") != null) { %>
            <div class="alert alert-success">¡Compra realizada con éxito! Gracias por tu pedido.</div>
        <% } %>
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger">Hubo un error al procesar la compra.</div>
        <% } %>

        <div class="card shadow-sm">
            <div class="card-body">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Título</th>
                            <th>ISBN</th>
                            <th class="text-end">Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Recuperamos el carrito de la sesión
                            ArrayList<Libro> lista = (ArrayList<Libro>) session.getAttribute("carrito");
                            double total = 0;

                            if (lista != null && !lista.isEmpty()) {
                                for (Libro l : lista) {
                                    total += l.getPrecio();
                        %>
                        <tr>
                            <td><%= l.getTitulo() %></td>
                            <td><%= l.getIsbn() %></td>
                            <td class="text-end"><%= l.getPrecio() %> €</td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="3" class="text-center p-4">
                                <em>Tu carrito está vacío. ¡Ve a comprar libros!</em>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            <div class="card-footer bg-white p-4">
                <div class="d-flex justify-content-between align-items-center">
                    <h3>Total a Pagar: <span class="text-danger fw-bold"><%= String.format("%.2f", total) %> €</span></h3>
                    
                    <div>
                        <a href="SrvCarrito?accion=vaciar" class="btn btn-outline-danger me-2">Vaciar Carrito</a>
                        
                        <%-- BOTÓN PAGAR (Solo aparece si hay libros) --%>
                        <% if (lista != null && !lista.isEmpty()) { %>
                            <a href="SrvCarrito?accion=pagar" class="btn btn-success btn-lg">✅ Pagar y Finalizar</a>
                        <% } %>
                    </div>
                </div>
                <div class="mt-3 text-end">
                    <a href="index.jsp" class="text-decoration-none">← Seguir comprando</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>