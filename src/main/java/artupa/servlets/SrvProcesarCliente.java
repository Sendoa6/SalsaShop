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
public class SrvProcesarCliente extends HttpServlet {
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
			if (modo_entrada.equals("modo_modificacion"))
			{
				String dni = request.getParameter("dni");
				BdOperaciones bdOperaciones = new BdOperaciones();
				bdOperaciones.abrirConexion();
				Cliente cliente = bdOperaciones.getCliente(dni);
				bdOperaciones.cerrarConexion();
				request.setAttribute("cliente",cliente);
			}
			
			ServletContext ct = getServletContext();
			RequestDispatcher rd = ct.getRequestDispatcher("/cliente.jsp");
			rd.forward(request,response);
		}
		else
		{
			response.sendRedirect("login.html");
		}
	}

}
