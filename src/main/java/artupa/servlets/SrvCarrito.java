package artupa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import artupa.bd.BdOperaciones;
import artupa.beans.Cliente;
import artupa.beans.Libro;

@WebServlet("/SrvCarrito")
public class SrvCarrito extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList<Libro> carrito = (ArrayList<Libro>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<Libro>();
            session.setAttribute("carrito", carrito);
        }

        String accion = request.getParameter("accion");
        String isbn = request.getParameter("isbn");

        BdOperaciones bd = new BdOperaciones(); 

        try {
            if (accion != null) {
                switch (accion) {
                case "anadir":
                    Libro l = bd.obtenerLibro(isbn);
                    if (l != null) {
                        // CAMBIO: Verificamos stock antes de añadir
                        if (l.getStock() > 0) {
                            carrito.add(l);
                            response.sendRedirect("SrvIndex?msg=ok");
                        } else {
                            // Si intenta añadir algo sin stock, redirigimos con error (opcional)
                            response.sendRedirect("SrvIndex?msg=sin_stock"); 
                        }
                    }
                    break;

                    case "eliminar":
                        for (int i = 0; i < carrito.size(); i++) {
                            if (carrito.get(i).getIsbn().equals(isbn)) {
                                carrito.remove(i);
                                break;
                            }
                        }
                        response.sendRedirect("carrito.jsp");
                        break;

                    case "pagar":
                        String nombreUsuario = (String) session.getAttribute("user");
                        
                        if (nombreUsuario == null) {
                            response.sendRedirect("login.jsp");
                            return;
                        }

                        // Recuperamos el ID real del cliente usando el nuevo método de BdOperaciones
                        int idCliente = bd.getIdClientePorUsuario(nombreUsuario);
                        
                        Cliente c = new Cliente();
                        c.setIdCliente(idCliente);
                        
                        boolean exito = bd.realizarCompra(c, carrito);
                        
                        if (exito) {
                            carrito.clear();
                            response.sendRedirect("carrito.jsp?mensaje=exito");
                        } else {
                            response.sendRedirect("carrito.jsp?mensaje=error");
                        }
                        break;
                }
            } else {
                response.sendRedirect("carrito.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.cerrarConexion();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}