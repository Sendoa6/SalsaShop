/*
 * Created on 23-may-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package artupa.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import artupa.bd.BdBase;
import artupa.bd.BdOperaciones;
import artupa.config.Configuracion;
import artupa.config.GestorConfiguracion;

;

/**
 * @author Administrador
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SrvValidarEntrada extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// String FICHERO_CONFIGURACION = "C:\\Tomcat
		// 5.5\\webapps\\ArtupaWeb\\WEB-INF\\artupa.properties";
		String FICHERO_CONFIGURACION = getServletConfig().getInitParameter(
				"fichero_propiedades");
		boolean cargaCorrecta = GestorConfiguracion
				.cargarConfiguracion(FICHERO_CONFIGURACION);
		if (!cargaCorrecta) {
			System.out
					.println("Fichero de configuración no cargado correctamente");
		} else {
			BdBase.inicializarParametrosConexion(Configuracion.getInstancia());
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
		boolean correcto = bdOperaciones.validarUsuario(user, password);
		bdOperaciones.cerrarConexion();
		if (correcto) {
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("user", user);
			ServletContext ct = getServletContext();
			RequestDispatcher rd = ct.getRequestDispatcher("/menu.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

}
