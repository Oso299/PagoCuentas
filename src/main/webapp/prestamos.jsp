<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Modelo.Prestamo" %>
<%
    List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
    int nro_cliente = (Integer) request.getAttribute("nro_cliente");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Préstamos</title>
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
    <h1 class="text-center mb-4 titulo-principal">Préstamos del Cliente <%= nro_cliente %></h1>
  

<!-- Formulario para agregar préstamo -->
<form action="PrestamoServlet" method="post" class="row g-3 mb-4">
    <input type="hidden" name="nro_cliente" value="<%= nro_cliente %>">
    <div class="col-md-3">
        <input type="number" step="0.01" name="monto_total" class="form-control form-control-sm" placeholder="Monto Total" required>
    </div>
    <div class="col-md-3">
        <input type="date" name="fecha_inicio" class="form-control form-control-sm" required>
    </div>
    <div class="col-md-3">
        <input type="number" name="cantidad_cuotas" class="form-control form-control-sm" placeholder="Cantidad de Cuotas" required>
    </div>
    <div class="col-md-3 d-flex align-items-end">
        <input type="submit" class="btn btn-success btn-sm w-60" value="Agregar Préstamo">
    </div>
</form>


    <!-- Tabla de préstamos -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Préstamo</th>
            <th>Monto Total</th>
            <th>Fecha Inicio</th>
            <th>Cantidad Cuotas</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(prestamos != null) {
                for(Prestamo p : prestamos) {
        %>
        <tr>
            <td><%= p.getId_prestamo() %></td>
           <td><%= String.format("%,.0f", p.getMonto_total()) %></td>
            <td><%= p.getFecha_inicio() %></td>
            <td><%= p.getCantidad_cuotas() %></td>
            <td>
                <a href="CuotaServlet?id_prestamo=<%= p.getId_prestamo() %>&nro_cliente=<%= nro_cliente %>"
                   class="btn btn-primary btn-sm">Ver Cuotas</a>
                <a href="PrestamoServlet?action=eliminar&id=<%= p.getId_prestamo() %>&nro_cliente=<%= nro_cliente %>"
                   class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar este préstamo?')">Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <a href="ClienteServlet" class="btn btn-secondary mt-3">Volver a Clientes</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
