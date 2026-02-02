package artupa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;

public class SrvNuevaCategoria extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre_categoria");

        BdOperaciones bd = new BdOperaciones();
        if(bd.abrirConexion()) {
            boolean ok = bd.insertarCategoria(nombre);
            bd.cerrarConexion();
            
            if(ok) {
                // Redirigimos al formulario de libro para que pueda usar la nueva categoría
                response.sendRedirect("nuevoLibro.jsp"); 
            } else {
                response.sendRedirect("nuevaCategoria.jsp?error=1");
            }
        }
    }
}