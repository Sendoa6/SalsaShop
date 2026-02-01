package artupa.beans;

import java.io.Serializable;
import java.sql.Date; 

public class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int idCliente;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2; 
    private String direccion;
    private Date fechaNacimiento;
    private String email;
    private String usuario;
    private String password;

    public Cliente() {
        super();
    }

    public Cliente(int idCliente, String dni, String nombre, String apellido1, String apellido2, 
                   String direccion, Date fechaNacimiento, String email, String usuario, String password) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
    }

    // ================= GETTERS Y SETTERS =================

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}