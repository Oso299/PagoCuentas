package Controlador;

import Modelo.Prestamo;
import Modelo.PrestamoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {
    private PrestamoDAO dao = new PrestamoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nroClienteStr = request.getParameter("nro_cliente");
        int nro_cliente = (nroClienteStr != null) ? Integer.parseInt(nroClienteStr) : 0;

        if (action == null) action = "listar";

        switch (action) {
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminarPrestamo(idEliminar);
                response.sendRedirect("PrestamoServlet?nro_cliente=" + nro_cliente);
                break;
            default: // listar
                List<Prestamo> prestamos = dao.listarPrestamos(nro_cliente);
                request.setAttribute("prestamos", prestamos);
                request.setAttribute("nro_cliente", nro_cliente);
                request.getRequestDispatcher("prestamos.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nro_cliente = Integer.parseInt(request.getParameter("nro_cliente"));
        double monto_total = Double.parseDouble(request.getParameter("monto_total"));
        java.sql.Date fecha_inicio = java.sql.Date.valueOf(request.getParameter("fecha_inicio"));
        int cantidad_cuotas = Integer.parseInt(request.getParameter("cantidad_cuotas"));

        Prestamo p = new Prestamo();
        p.setNro_cliente(nro_cliente);
        p.setMonto_total(monto_total);
        p.setFecha_inicio(fecha_inicio);
        p.setCantidad_cuotas(cantidad_cuotas);

        dao.agregarPrestamo(p);
        response.sendRedirect("PrestamoServlet?nro_cliente=" + nro_cliente);
    }
}
