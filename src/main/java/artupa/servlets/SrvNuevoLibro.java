package artupa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;

public class SrvNuevoLibro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idAutor = Integer.parseInt(request.getParameter("id_autor"));
        int idEdi = Integer.parseInt(request.getParameter("id_editorial"));
        int idCat = Integer.parseInt(request.getParameter("id_categoria"));

        BdOperaciones bd = new BdOperaciones();
        if(bd.abrirConexion()) {
            boolean ok = bd.insertarLibro(isbn, titulo, precio, stock, idAutor, idEdi, idCat);
            bd.cerrarConexion();
            
            if(ok) {
                response.sendRedirect("SrvIndex"); // O a donde quieras volver
            } else {
                response.sendRedirect("nuevoLibro.jsp?error=1");
            }
        }
    }
}