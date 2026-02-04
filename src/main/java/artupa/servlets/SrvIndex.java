package artupa.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import artupa.bd.BdOperaciones;
import artupa.beans.Libro;

public class SrvIndex extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8"); // Para tildes y ñ
        HttpSession session = request.getSession();
        String accion = request.getParameter("accion");
        
        System.out.println("--- SrvIndex invocado. Acción: " + accion + " ---");
        
        // --- 1. LÓGICA DE CERRAR SESIÓN (Prioridad Máxima) ---
        if ("cerrar".equals(accion)) {
            System.out.println("Cerrando sesión...");
            session.invalidate(); // Destruye la sesión
            response.sendRedirect("login.jsp"); // Manda al login corregido
            return; // Detiene la ejecución aquí
        }

        String userForm = request.getParameter("user");
        String passForm = request.getParameter("password");
        
        // Verificar si ya hay un usuario logueado en la sesión
        String usuarioLogueado = (String) session.getAttribute("user");
        BdOperaciones bd = new BdOperaciones();
        
        // --- 2. LÓGICA DE LOGIN ---
        if (usuarioLogueado == null) {
            // Caso A: Intenta loguearse desde el formulario
            if (userForm != null && passForm != null) {
                if (bd.validarUsuario(userForm, passForm)) {
                    // LOGIN CORRECTO
                    session.setAttribute("user", userForm);
                    
                    // Recuperar Rol Admin
                    int rol = bd.getRolUsuario(userForm);
                    session.setAttribute("rolAdmin", rol);
                    
                    usuarioLogueado = userForm; // Marcamos para que cargue el menú abajo
                } else {
                    // LOGIN FALLIDO
                    request.setAttribute("error", "Usuario o contraseña incorrectos.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
            } else {
                // Caso B: No hay sesión y no hay datos del form -> Mandar al Login
                response.sendRedirect("login.jsp");
                return;
            }
        }
        
        // --- 3. CARGAR DATOS Y MOSTRAR MENÚ ---
        // Si llegamos aquí, el usuario YA está dentro.
        List<Libro> listaLibros = bd.getLibrosConAutor();
        request.setAttribute("listaLibros", listaLibros);
        
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