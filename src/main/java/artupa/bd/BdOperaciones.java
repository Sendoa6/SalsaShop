package artupa.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import artupa.beans.Cliente;

public class BdOperaciones extends BdBase {

    public BdOperaciones() {
        super();
    }

    // 1. NUEVAS FUNCIONES PARA EL REGISTRO (Vitales para registro.jsp)

    /**
     * Comprueba si el usuario y contraseña coinciden para el LOGIN.
     */
    public boolean validarUsuario(String user, String password) {
        boolean correcto = false;
        try {
            // Ahora buscamos en la tabla 'clientes' que es donde guardamos todo
            String sql = "SELECT id_cliente FROM clientes WHERE usuario = ? AND password = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                correcto = true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correcto;
    }

    /**
     * Comprueba si ya existe un usuario o email para evitar duplicados.
     */
    public boolean existeUsuario(String usuario, String email) {
        boolean existe = false;
        try {
            String sql = "SELECT id_cliente FROM clientes WHERE usuario = ? OR email = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, email);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    /**
     * Calcula el siguiente ID disponible (porque tu tabla no es AutoIncrement).
     */
    public int obtenerNuevoId() {
        int maxId = 0;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_cliente) FROM clientes");
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }

    /**
     * Inserta un cliente con TODOS los campos nuevos.
     */
    public boolean registrarCliente(Cliente c) {
        boolean correcto = true;
        try {
            int nuevoId = obtenerNuevoId(); // Calculamos el ID automáticamente
            
            // La SQL debe coincidir con tus columnas de la base de datos
            String sql = "INSERT INTO clientes (id_cliente, dni, nombre, apellido1, apellido2, direccion, fecha_nacimiento, email, usuario, password) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, nuevoId);
            ps.setString(2, c.getDni());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido1());
            ps.setString(5, c.getApellido2()); // Si es null, se guarda como NULL
            ps.setString(6, c.getDireccion());
            ps.setDate(7, c.getFechaNacimiento()); // Puede ser null si no la pediste
            ps.setString(8, c.getEmail());
            ps.setString(9, c.getUsuario());
            ps.setString(10, c.getPassword());

            ps.executeUpdate();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrarCliente: " + e.getMessage());
            correcto = false;
        }
        return correcto;
    }

    // 2. FUNCIONES DE LISTADO Y MODIFICACIÓN (Actualizadas a la nueva tabla)

    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            String sql = "SELECT * FROM clientes";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido1(rs.getString("apellido1"));
                c.setApellido2(rs.getString("apellido2"));
                c.setDireccion(rs.getString("direccion"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEmail(rs.getString("email"));
                c.setUsuario(rs.getString("usuario"));
                
                clientes.add(c);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente getCliente(String dni) {
        Cliente c = null;
        try {
            String sql = "SELECT * FROM clientes WHERE dni = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, dni);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido1(rs.getString("apellido1"));
                c.setApellido2(rs.getString("apellido2"));
                c.setDireccion(rs.getString("direccion"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setEmail(rs.getString("email"));
                c.setUsuario(rs.getString("usuario"));
                c.setPassword(rs.getString("password"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean modificarCliente(Cliente c) {
        boolean correcto = true;
        try {
            String sql = "UPDATE clientes SET nombre=?, apellido1=?, apellido2=?, direccion=?, email=?, usuario=? WHERE dni=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido1());
            ps.setString(3, c.getApellido2());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getUsuario());
            ps.setString(7, c.getDni()); // El WHERE va al final

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }

    public boolean eliminarCliente(String dni) {
        boolean correcto = true;
        try {
            String sql = "DELETE FROM clientes WHERE dni = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, dni);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }
}