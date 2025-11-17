package Controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Usuario;
import Modelo.UsuarioDAO;

@WebServlet("/CambiarClaveServlet")
public class CambiarClaveServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String claveActual = request.getParameter("claveActual");
        String nuevaClave = request.getParameter("nuevaClave");

        // Verifica si el usuario existe y la clave actual es correcta
        boolean ok = usuarioDAO.cambiarClave(usuario, claveActual, nuevaClave);

        if (ok) {
            request.setAttribute("ok", "Contraseña cambiada con éxito");
        } else {
            request.setAttribute("error", "Usuario o clave actual incorrecta");
        }

        request.getRequestDispatcher("cambiarClave.jsp").forward(request, response);
    }
}
