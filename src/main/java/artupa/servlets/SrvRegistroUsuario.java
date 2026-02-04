package artupa.servlets;

import java.io.IOException;
import java.sql.Date; 
import javax.servlet.ServletException;
import javax.servlet.http.*;
import artupa.bd.BdOperaciones;
import artupa.beans.Cliente;

public class SrvRegistroUsuario extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // 1. Recoger parámetros
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
        
        // 2. Validaciones básicas
        if (usuario == null || pass == null || email == null || dni == null) {
            sesion.setAttribute("error", "Faltan datos obligatorios.");
            response.sendRedirect("registroUsuario.jsp");
            return;
        }

        // 3. Crear Objeto Cliente
        Cliente c = new Cliente();
        c.setUsuario(usuario);
        c.setEmail(email);
        c.setPassword(pass);
        c.setDni(dni);
        c.setNombre(nombre);
        c.setApellido1(apellido1);
        c.setApellido2(apellido2);
        c.setDireccion(direccion);
        
        // Convertir fecha (String -> java.sql.Date)
        try {
            if(fechaStr != null && !fechaStr.isEmpty()) {
                c.setFechaNacimiento(Date.valueOf(fechaStr));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Si falla la fecha, se dejará null
        }

        // 4. Guardar en BD
        BdOperaciones bd = new BdOperaciones();
        
        // Verificar si existe antes
        if (bd.existeUsuario(usuario, email)) {
            sesion.setAttribute("error", "El usuario o email ya existen.");
            response.sendRedirect("registroUsuario.jsp");
            return;
        }

        boolean registrado = bd.registrarCliente(c);
        
        if (registrado) {
            // ÉXITO: Mandar al LOGIN.JSP
            sesion.setAttribute("success", "¡Registro completado! Inicia sesión.");
            response.sendRedirect("login.jsp");
        } else {
            // ERROR
            sesion.setAttribute("error", "Error al registrar en la base de datos.");
            response.sendRedirect("registroUsuario.jsp");
        }
    }
}