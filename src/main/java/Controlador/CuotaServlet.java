package Controlador;

import Modelo.Cuota;
import Modelo.CuotaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CuotaServlet")
public class CuotaServlet extends HttpServlet {
    private CuotaDAO dao = new CuotaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  	
    	// Proteccion del Sistema
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuarioLogeado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    	
        String action = request.getParameter("action");
        int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));

        if (action == null) action = "listar";

        switch (action) {
            case "pagada":
                int idCuota = Integer.parseInt(request.getParameter("id"));
                dao.actualizarEstado(idCuota, "Pagada");
                response.sendRedirect("CuotaServlet?id_prestamo=" + id_prestamo + "&nro_cliente=" + request.getParameter("nro_cliente"));
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminarCuota(idEliminar);
                response.sendRedirect("CuotaServlet?id_prestamo=" + id_prestamo + "&nro_cliente=" + request.getParameter("nro_cliente"));
                break;
            default: // listar
                List<Cuota> cuotas = dao.listarCuotas(id_prestamo);
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("id_prestamo", id_prestamo);
                request.setAttribute("nro_cliente", request.getParameter("nro_cliente"));
                request.getRequestDispatcher("cuotas.jsp").forward(request, response);
                break;
        }
    }
}
