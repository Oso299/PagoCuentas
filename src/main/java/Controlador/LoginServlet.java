package Controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Usuario;
import Modelo.UsuarioDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// LoginServlet.java
    	String nombre = request.getParameter("nombre"); // debe coincidir con el name del input
    	String clave = request.getParameter("clave");

    	Usuario u = usuarioDAO.validarLogin(nombre, clave);


        if (u != null) {
            // Guardamos sesión
            request.getSession().setAttribute("usuarioLogeado", u);
            response.sendRedirect("ClienteServlet"); // tu pantalla principal
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrecta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}