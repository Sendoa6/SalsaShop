package artupa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;

public class SrvNuevaEditorial extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre_editorial");

        BdOperaciones bd = new BdOperaciones();
        if(bd.abrirConexion()) {
            boolean ok = bd.insertarEditorial(nombre);
            bd.cerrarConexion();
            
            if(ok) {
                // Redirigimos al formulario de libro
                response.sendRedirect("nuevoLibro.jsp"); 
            } else {
                response.sendRedirect("nuevaEditorial.jsp?error=1");
            }
        }
    }
}