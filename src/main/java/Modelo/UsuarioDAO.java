package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	public Usuario validarLogin(String nombre, String clave) {
	    if (nombre == null || clave == null) return null; // previene NPE
	    nombre = nombre.trim();
	    clave = clave.trim();

	    String sql = "SELECT * FROM usuario WHERE nombre = ? AND clave = ?";
	    try (Connection con = Conexion.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, nombre);
	        ps.setString(2, clave);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Usuario u = new Usuario();
	            u.setId(rs.getInt("id"));
	            u.setNombre(rs.getString("nombre"));
	            return u;
	        }
	    } catch (Exception e) { e.printStackTrace(); }

	    return null; // no existe
	}


    
    
	public boolean cambiarClave(String usuario, String claveActual, String nuevaClave) {
	    String sql = "UPDATE usuario SET clave = ? WHERE nombre = ? AND clave = ?";
	    boolean actualizado = false;
	    try (Connection con = Conexion.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, nuevaClave.trim());   // <--- trim()
	        ps.setString(2, usuario.trim());      // <--- trim()
	        ps.setString(3, claveActual.trim());  // <--- trim()

	        int filas = ps.executeUpdate();
	        actualizado = filas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return actualizado;
	}

    
    
    
    
    
}
