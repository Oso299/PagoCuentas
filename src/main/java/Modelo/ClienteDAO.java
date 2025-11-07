
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nro_cliente";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNro_cliente(rs.getInt("nro_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Agregar cliente
    public void agregarCliente(Cliente c) {
        String sql = "INSERT INTO cliente(nombre, direccion, telefono) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarCliente(Cliente c) {
        String sql = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ? WHERE nro_cliente = ?";
        boolean actualizado = false;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.setInt(4, c.getNro_cliente());
            int filas = ps.executeUpdate();
            actualizado = filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }




    // Eliminar cliente
    public void eliminarCliente(int nro_cliente) {
        String sql = "DELETE FROM cliente WHERE nro_cliente=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_cliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar cliente por ID
    public Cliente buscarCliente(int nro_cliente) {
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE nro_cliente=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_cliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setNro_cliente(rs.getInt("nro_cliente"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    
    public List<Cliente> buscarPorIdONombre(String valor) {
        List<Cliente> lista = new ArrayList<>();
        String sql;
        boolean esNumero = valor.matches("\\d+"); // Si es número, busca por ID; sino, por nombre

        if (esNumero) {
            sql = "SELECT * FROM cliente WHERE nro_cliente = ?";
        } else {
            sql = "SELECT * FROM cliente WHERE LOWER(nombre) LIKE LOWER(?)";
        }

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (esNumero) {
                ps.setInt(1, Integer.parseInt(valor));
            } else {
                ps.setString(1, "%" + valor + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNro_cliente(rs.getInt("nro_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
