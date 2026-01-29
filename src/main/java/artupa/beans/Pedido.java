/*
 * Created on 25-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package artupa.beans;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Pedido {
	private String dni;
	private int numPedido;
	private String detallePedido;
	public Pedido()
	{
		super();
	}
	public Pedido(String dni,int numPedido,String detallePedido)
	{
		super();
		this.dni=dni;
		this.numPedido=numPedido;
		this.detallePedido=detallePedido;
	}
	/**
	 * @return Returns the detallePedido.
	 */
	public String getDetallePedido() {
		return detallePedido;
	}
	/**
	 * @param detallePedido The detallePedido to set.
	 */
	public void setDetallePedido(String detallePedido) {
		this.detallePedido = detallePedido;
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
	 * @return Returns the numPedido.
	 */
	public int getNumPedido() {
		return numPedido;
	}
	/**
	 * @param numPedido The numPedido to set.
	 */
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
}
