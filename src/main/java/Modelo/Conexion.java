package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	 private static final String URL = "jdbc:postgresql://dpg-d472fcfdiees73dgmoe0-a.oregon-postgres.render.com:5432/cuentas_a_pagar";
	    private static final String USER = "cuentas_a_pagar_user";
	    private static final String PASS = "WWJ5DuXupq87Sc9qbsjz1kzl5cvEt0Mo";


    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

