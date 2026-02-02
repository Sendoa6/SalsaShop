package artupa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;

public class SrvNuevoAutor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre_autor");

        BdOperaciones bd = new BdOperaciones();
        if(bd.abrirConexion()) {
            boolean ok = bd.insertarAutor(nombre);
            bd.cerrarConexion();
            
            if(ok) {
                // Redirigimos al formulario de libro para que vea el nuevo ID si quiere
                response.sendRedirect("nuevoLibro.jsp"); 
            } else {
                response.sendRedirect("nuevoAutor.jsp?error=1");
            }
        }
    }
}