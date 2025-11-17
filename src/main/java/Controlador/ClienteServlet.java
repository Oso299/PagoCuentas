package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
    private ClienteDAO dao = new ClienteDAO();
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Proteccion del Sistema
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuarioLogeado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        String buscar = request.getParameter("buscar"); // <-- nuevo parámetro de búsqueda

        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Cliente c = dao.buscarCliente(id);
                request.setAttribute("cliente", c);
                request.setAttribute("clientes", dao.listarClientes());
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
                break;

            case "eliminar":
                int delId = Integer.parseInt(request.getParameter("id"));
                dao.eliminarCliente(delId);
                response.sendRedirect("ClienteServlet");
                break;

            default: // listar o buscar
                List<Cliente> lista;
                if (buscar != null && !buscar.trim().isEmpty()) {
                    lista = dao.buscarPorIdONombre(buscar); // 🔍 nuevo método en DAO
                } else {
                    lista = dao.listarClientes();
                }
                request.setAttribute("clientes", lista);
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
                break;
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nro_clienteStr = request.getParameter("nro_cliente");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setDireccion(direccion);
        c.setTelefono(telefono);

        if (nro_clienteStr != null && !nro_clienteStr.isEmpty()) {
            c.setNro_cliente(Integer.parseInt(nro_clienteStr));
            boolean ok = dao.actualizarCliente(c);
            if (!ok) {
                System.out.println("❌ No se pudo actualizar el cliente: " + c.getNro_cliente());
            }
        } else {
            dao.agregarCliente(c);
        }

        response.sendRedirect("ClienteServlet");
    }



}
