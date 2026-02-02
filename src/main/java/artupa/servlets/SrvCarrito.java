package artupa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;
import artupa.beans.Cliente;
import artupa.beans.Libro;

public class SrvCarrito extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        
        // 1. Inicializar el carrito en la sesión si no existe
        ArrayList<Libro> carrito = (ArrayList<Libro>) sesion.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<Libro>();
            sesion.setAttribute("carrito", carrito);
        }

        BdOperaciones bd = new BdOperaciones();   
        bd.abrirConexion();   

        if ("agregar".equals(accion)) {
            // --- LÓGICA DE AÑADIR ---
            String isbn = request.getParameter("isbn");
            Libro l = bd.obtenerLibro(isbn);
            
            if (l != null && l.getStock() > 0) {
                carrito.add(l); // Lo añadimos a la lista
            }
            bd.cerrarConexion();
            // Redirigimos al JSP del carrito para que el usuario vea lo que añadió
            response.sendRedirect("carrito.jsp"); 

        } else if ("pagar".equals(accion)) {
            // --- LÓGICA DE PAGAR ---
            Cliente cliente = (Cliente) sesion.getAttribute("usuarioLogueado");
            
            // Si no está logueado, lo mandamos al login
            if (cliente == null) {
                bd.cerrarConexion();
                response.sendRedirect("login.html"); 
                return;
            }

            // Realizamos la compra en BD
            boolean exito = bd.realizarCompra(cliente, carrito);
            bd.cerrarConexion();

            if (exito) {
                sesion.removeAttribute("carrito"); // Vaciamos el carrito
                // Redirigir a una página de éxito o al inicio con un mensaje
                response.sendRedirect("carrito.jsp?mensaje=compra_ok"); 
            } else {
                response.sendRedirect("carrito.jsp?error=fallo_db");
            }
        } else if ("vaciar".equals(accion)) {
            sesion.removeAttribute("carrito");
            response.sendRedirect("carrito.jsp");
        }
    }
}