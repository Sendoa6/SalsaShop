package artupa.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import artupa.bd.BdOperaciones;
import artupa.beans.Libro;

public class SrvIndex extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        
        BdOperaciones bd = new BdOperaciones();
        
        // 1. Validar login (si viene del login.html) o recuperar de sesión
        if (user != null && bd.validarUsuario(user, pass)) {
            session.setAttribute("user", user);
        }

        // 2. LA CLAVE: Cargar los libros SIEMPRE antes de ir al menú
        // Si no haces esto, el JSP recibirá una lista vacía
        List<Libro> listaLibros = bd.getLibrosConAutor();
        
        // 3. Pasar la lista al objeto 'request' con el nombre "listaLibros"
        request.setAttribute("listaLibros", listaLibros);
        
        // 4. Redirigir al JSP
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}