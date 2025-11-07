package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuotaDAO {

    public void agregarCuota(Cuota c) {
        String sql = "INSERT INTO cuota(id_prestamo, nro_cuota, fecha_pago, monto, estado) VALUES (?,?,?,?,?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getId_prestamo());
            ps.setInt(2, c.getNro_cuota());
            ps.setDate(3, c.getFecha_pago());
            ps.setDouble(4, c.getMonto());
            ps.setString(5, c.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Cuota> listarCuotas(int id_prestamo) {
        List<Cuota> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuota WHERE id_prestamo=? ORDER BY nro_cuota";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_prestamo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuota c = new Cuota(
                            rs.getInt("id_cuota"),
                            rs.getInt("id_prestamo"),
                            rs.getInt("nro_cuota"),
                            rs.getDate("fecha_pago"),
                            rs.getDouble("monto"),
                            rs.getString("estado")
                    );
                    lista.add(c);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void actualizarEstado(int id_cuota, String estado) {
        String sql = "UPDATE cuota SET estado=? WHERE id_cuota=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, id_cuota);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void eliminarCuota(int id_cuota) {
        String sql = "DELETE FROM cuota WHERE id_cuota=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_cuota);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
