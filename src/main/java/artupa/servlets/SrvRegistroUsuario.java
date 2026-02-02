package artupa.servlets;

import java.io.IOException;
import java.sql.Date; 
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;
import artupa.beans.Cliente;

// IMPORTANTE: NO pongas @WebServlet aquí porque ya lo tienes en el web.xml
public class SrvRegistroUsuario extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        String direccion = request.getParameter("direccion");
        String fechaStr = request.getParameter("fechaNacimiento");
        
        HttpSession sesion = request.getSession();

        // 1. Validaciones básicas
        if (usuario == null || pass == null || email == null || dni == null || nombre == null || apellido1 == null) {
            sesion.setAttribute("error", "Faltan datos obligatorios.");
            response.sendRedirect("registroUsuario.jsp"); 
            return;
        }

        BdOperaciones bd = new BdOperaciones();
        boolean conexionAbierta = bd.abrirConexion();

        if (!conexionAbierta) {
            sesion.setAttribute("error", "Error de conexión con la base de datos.");
            response.sendRedirect("registroUsuario.jsp");
            return;
        }

        try {
            // 2. Comprobar duplicados
            if (bd.existeUsuario(usuario, email)) {
                bd.cerrarConexion();
                sesion.setAttribute("error", "El usuario o el email ya existen.");
                response.sendRedirect("registroUsuario.jsp");
                return;
            }

            // 3. Crear objeto y guardar
            Cliente c = new Cliente();
            c.setDni(dni);
            c.setNombre(nombre);
            c.setApellido1(apellido1);
            c.setApellido2(apellido2);
            c.setDireccion(direccion);
            
            if (fechaStr != null && !fechaStr.isEmpty()) {
                try {
                    c.setFechaNacimiento(Date.valueOf(fechaStr));
                } catch (Exception e) {
                    c.setFechaNacimiento(null);
                }
            } else {
                c.setFechaNacimiento(null);
            }

            c.setUsuario(usuario);
            c.setEmail(email);
            c.setPassword(pass); 
            
            boolean registrado = bd.registrarCliente(c);
            bd.cerrarConexion();

            if (registrado) {
                sesion.setAttribute("success", "¡Registro completado! Ahora inicia sesión.");
                response.sendRedirect("login.html"); 
            } else {
                sesion.setAttribute("error", "Hubo un error al guardar en la base de datos.");
                response.sendRedirect("registroUsuario.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Esto te dirá el error real en la consola roja
            bd.cerrarConexion();
            sesion.setAttribute("error", "Error interno en el servidor.");
            response.sendRedirect("registroUsuario.jsp");
        }
    }
}