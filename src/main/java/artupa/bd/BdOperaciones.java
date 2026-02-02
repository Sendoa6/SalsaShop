package artupa.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import artupa.beans.Cliente;
import artupa.beans.Libro;

public class BdOperaciones extends BdBase {

    public BdOperaciones() {
        super();
    }

    // ==========================================
    // 1. FUNCIONES PARA EL REGISTRO Y LOGIN
    // ==========================================
    
    public boolean validarUsuario(String user, String password) {
        boolean correcto = false;
        try {
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

    public boolean registrarCliente(Cliente c) {
        boolean correcto = true;
        try {
            int nuevoId = obtenerNuevoId();
            String sql = "INSERT INTO clientes (id_cliente, dni, nombre, apellido1, apellido2, direccion, fecha_nacimiento, email, usuario, password) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, nuevoId);
            ps.setString(2, c.getDni());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido1());
            ps.setString(5, c.getApellido2());
            ps.setString(6, c.getDireccion());
            ps.setDate(7, c.getFechaNacimiento());
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

    // ==========================================
    // 2. FUNCIONES DE CLIENTES (CRUD)
    // ==========================================

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
            ps.setString(7, c.getDni());
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

    // ==========================================
    // 3. NUEVAS FUNCIONES PARA EL CARRITO (CORREGIDAS)
    // ==========================================

    // Método para recuperar un libro por su ISBN
    public Libro obtenerLibro(String isbn) {
        Libro l = null;
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        try {
            // CORREGIDO: Usamos 'conexion' en vez de 'con'
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l = new Libro();
                l.setIsbn(rs.getString("isbn"));
                l.setTitulo(rs.getString("titulo"));
                l.setPrecio(rs.getDouble("precio"));
                l.setStock(rs.getInt("stock"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }
        return l;
    }

    // Método para PAGAR: Inserta la compra y RESTA el stock
    public boolean realizarCompra(Cliente cliente, ArrayList<Libro> carrito) {
        boolean exito = false;
        try {
            // CORREGIDO: Usamos 'conexion' en todos los pasos
            conexion.setAutoCommit(false); // Inicio transacción

            // A) Calcular ID de Compra
            int idCompra = 1;
            Statement st = conexion.createStatement();
            ResultSet rsId = st.executeQuery("SELECT MAX(id_compra) FROM compras");
            if (rsId.next()) idCompra = rsId.getInt(1) + 1;
            rsId.close();

            // B) Insertar en tabla COMPRAS
            String sqlCompra = "INSERT INTO compras (id_compra, id_cliente, fecha) VALUES (?, ?, CURDATE())";
            PreparedStatement psCompra = conexion.prepareStatement(sqlCompra);
            psCompra.setInt(1, idCompra);
            psCompra.setInt(2, cliente.getIdCliente());
            psCompra.executeUpdate();
            psCompra.close();
            
            // C) Preparar IDs para lineas de compra
            int idLinea = 1;
            ResultSet rsIdLinea = st.executeQuery("SELECT MAX(id_linea) FROM linea_compra");
            if (rsIdLinea.next()) idLinea = rsIdLinea.getInt(1) + 1;
            rsIdLinea.close();
            st.close();

            // D) Insertar detalle y restar stock
            String sqlLinea = "INSERT INTO linea_compra (id_linea, id_compra, isbn, cantidad, precio_unitario) VALUES (?, ?, ?, 1, ?)";
            String sqlUpdateStock = "UPDATE libros SET stock = stock - 1 WHERE isbn = ?";
            
            PreparedStatement psLinea = conexion.prepareStatement(sqlLinea);
            PreparedStatement psStock = conexion.prepareStatement(sqlUpdateStock);

            for (Libro libro : carrito) {
                // Insertar línea de compra
                psLinea.setInt(1, idLinea++);
                psLinea.setInt(2, idCompra);
                psLinea.setString(3, libro.getIsbn());
                psLinea.setDouble(4, libro.getPrecio());
                psLinea.executeUpdate();

                // RESTAR STOCK
                psStock.setString(1, libro.getIsbn());
                psStock.executeUpdate();
            }
            
            psLinea.close();
            psStock.close();

            conexion.commit(); // Confirmar cambios
            exito = true;
            conexion.setAutoCommit(true); // Restaurar modo normal

        } catch (Exception e) {
            try { conexion.rollback(); } catch (Exception ex) {} // Deshacer cambios si falla
            e.printStackTrace();
        }
        return exito;
    }
}