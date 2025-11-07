<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Modelo.Cuota" %>
<%
    List<Cuota> cuotas = (List<Cuota>) request.getAttribute("cuotas");
    int id_prestamo = (Integer) request.getAttribute("id_prestamo");
    int nro_cliente = Integer.parseInt(request.getParameter("nro_cliente"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Cuotas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
     
         
    <style>
    /* Solo aplica a este h1 con la clase titulo-principal */
    .titulo-principal {
        font-family: 'Montserrat', sans-serif;
        font-weight: 700; /* opcional: más grueso */
    }
</style>
     
</head>
<body>
<div class="container mt-4">

     <h1 class="text-center mb-4 titulo-principal">Cuotas del Préstamo <%= id_prestamo %></h1>   

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Nro Cuota</th>
            <th>Fecha Pago</th>
            <th>Monto</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(cuotas != null) {
                for(Cuota c : cuotas) {
        %>
        <tr>
            <td><%= c.getNro_cuota() %></td>
            <td><%= c.getFecha_pago() %></td>
            <td><%= c.getMonto() %></td>
            <td><%= c.getEstado() %></td>
            <td>
                <% if(!c.getEstado().equals("Pagada")) { %>
                <a href="CuotaServlet?action=pagada&id=<%= c.getId_cuota() %>&id_prestamo=<%= id_prestamo %>&nro_cliente=<%= nro_cliente %>"
                   class="btn btn-success btn-sm">Marcar como Pagada</a>
                <% } %>
                <a href="CuotaServlet?action=eliminar&id=<%= c.getId_cuota() %>&id_prestamo=<%= id_prestamo %>&nro_cliente=<%= nro_cliente %>"
                   class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar esta cuota?')">Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <a href="PrestamoServlet?nro_cliente=<%= nro_cliente %>" class="btn btn-secondary mt-3">Volver a Préstamos</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
