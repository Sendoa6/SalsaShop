/*
 * Created on 24-may-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package artupa.servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import artupa.bd.BdOperaciones;
import artupa.beans.Cliente;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SrvActualizarCliente extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service (HttpServletRequest request,HttpServletResponse response)
	throws IOException,ServletException
	{				
		HttpSession sesion = request.getSession(false);	
		if (sesion!=null)
		{			
			String modo_entrada = request.getParameter("modo_entrada");
			
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			int edad = Integer.parseInt(request.getParameter("edad"));
			String direccion = request.getParameter("direccion");
			int codPostal = Integer.parseInt(request.getParameter("codPostal"));
			String localidad = request.getParameter("localidad");
			int telefono = Integer.parseInt(request.getParameter("telefono"));
			Cliente cliente = new Cliente(dni,nombre,apellido,edad,direccion,codPostal,localidad,telefono);
			
			BdOperaciones bdOperaciones = new BdOperaciones();
			bdOperaciones.abrirConexion();
			if (modo_entrada.equals("modo_alta"))
			{
				bdOperaciones.insertarCliente(cliente);
			}
			else
			{
				bdOperaciones.modificarCliente(cliente);
			}
			bdOperaciones.cerrarConexion();
			ServletContext ct = getServletContext();
			RequestDispatcher rd = ct.getRequestDispatcher("/SrvClientes");
			rd.forward(request,response);
		}
		else
		{
			response.sendRedirect("login.html");
		}
	}

}
