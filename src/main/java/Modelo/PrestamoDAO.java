package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    // Listar préstamos de un cliente
    public List<Prestamo> listarPrestamos(int nro_cliente) {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo WHERE nro_cliente=? ORDER BY id_prestamo";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_cliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo(
                            rs.getInt("id_prestamo"),
                            rs.getInt("nro_cliente"),
                            rs.getDouble("monto_total"),
                            rs.getDate("fecha_inicio"),
                            rs.getInt("cantidad_cuotas")
                    );
                    lista.add(p);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void agregarPrestamo(Prestamo p) {
        String sql = "INSERT INTO prestamo(nro_cliente, monto_total, fecha_inicio, cantidad_cuotas) VALUES (?,?,?,?) RETURNING id_prestamo";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getNro_cliente());
            ps.setDouble(2, p.getMonto_total());
            ps.setDate(3, p.getFecha_inicio());
            ps.setInt(4, p.getCantidad_cuotas());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idPrestamo = rs.getInt(1);

                // Generar cuotas automáticamente
                double cuotaMonto = p.getMonto_total() / p.getCantidad_cuotas();
                java.util.Date fecha = new java.util.Date(p.getFecha_inicio().getTime());

                for (int i = 1; i <= p.getCantidad_cuotas(); i++) {
                    java.sql.Date fechaPago = new java.sql.Date(fecha.getTime());
                    new CuotaDAO().agregarCuota(new Cuota(0, idPrestamo, i, fechaPago, cuotaMonto, "Pendiente"));

                    // Sumar un mes para la siguiente cuota
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.setTime(fecha);
                    cal.add(java.util.Calendar.MONTH, 1);
                    fecha = cal.getTime();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Eliminar préstamo (las cuotas se eliminan en cascada)
    public void eliminarPrestamo(int id_prestamo) {
        String sql = "DELETE FROM prestamo WHERE id_prestamo=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_prestamo);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Buscar préstamo por ID
    public Prestamo buscarPrestamo(int id_prestamo) {
        Prestamo p = null;
        String sql = "SELECT * FROM prestamo WHERE id_prestamo=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_prestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Prestamo(
                            rs.getInt("id_prestamo"),
                            rs.getInt("nro_cliente"),
                            rs.getDouble("monto_total"),
                            rs.getDate("fecha_inicio"),
                            rs.getInt("cantidad_cuotas")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return p;
    }
}
