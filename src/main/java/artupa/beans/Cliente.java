/*
 * Created on 25-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package artupa.beans;

import java.io.Serializable;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return Returns the apellido.
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido The apellido to set.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return Returns the dni.
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni The dni to set.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return Returns the edad.
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad The edad to set.
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String dni;
	private String nombre;
	private String apellido;
	/**
	 * @return Returns the codPostal.
	 */
	public int getCodPostal() {
		return codPostal;
	}
	/**
	 * @param codPostal The codPostal to set.
	 */
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	/**
	 * @return Returns the direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion The direccion to set.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return Returns the localidad.
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad The localidad to set.
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return Returns the telefono.
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	private int edad;
	private String direccion;
	private int codPostal;
	private String localidad;
	private int telefono;
	public Cliente()
	{
		super();
		
	}
	public Cliente(String dni,String nombre,String apellido,int edad, String direccion, int codPostal, String localidad, int telefono)
	{
		super();
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.edad=edad;
		this.direccion=direccion;
		this.codPostal=codPostal;
		this.localidad=localidad;
		this.telefono=telefono;
	}
}
