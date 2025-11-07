<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Modelo.Cliente" %>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Clientes</title>
    <!-- Bootstrap CSS -->
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
   <h1 class="text-center mb-4 titulo-principal">Clientes</h1>
    
    <!-- Formulario para consultar cliente -->
<form action="ClienteServlet" method="get" class="mb-3 d-flex gap-2 flex-wrap">
    <input type="text" name="buscar" class="form-control form-control-sm" placeholder="Buscar por ID o Nombre" style="width: 220px;">
    <button type="submit" class="btn btn-primary btn-sm">Buscar</button>
    <a href="ClienteServlet" class="btn btn-secondary btn-sm">Mostrar Todos</a>
</form>
    
<!-- Formulario para agregar cliente -->
<form action="ClienteServlet" method="post" class="row g-3 mb-4">
    <div class="col-md-3">
        <input type="text" name="nombre" class="form-control form-control-sm" placeholder="Nombre" required>
    </div>
    <div class="col-md-3">
        <input type="text" name="direccion" class="form-control form-control-sm" placeholder="Dirección" required>
    </div>
    <div class="col-md-3">
        <input type="text" name="telefono" class="form-control form-control-sm" placeholder="Teléfono" required>
    </div>
    <div class="col-md-3 d-flex align-items-end">
        <input type="submit" class="btn btn-success btn-sm w-60" value="Agregar Cliente">
    </div>
</form>

<!-- Tabla de clientes -->
<table class="table table-striped table-bordered">
    <thead class="table-dark">
    <tr>
        <th>Nro Cliente</th>
        <th>Nombre</th>
        <th>Dirección</th>
        <th>Teléfono</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (clientes != null) {
            for (Cliente c : clientes) {
    %>
    <tr>
        <td><%= c.getNro_cliente() %></td>
        <td>
            <form action="ClienteServlet" method="post" class="d-flex gap-1">
                <input type="hidden" name="nro_cliente" value="<%= c.getNro_cliente() %>">
                <input type="text" name="nombre" value="<%= c.getNombre() %>" class="form-control form-control-sm" style="width:140px;">
        </td>
        <td>
                <input type="text" name="direccion" value="<%= c.getDireccion() %>" class="form-control form-control-sm" style="width:180px;">
        </td>
        <td>
                <input type="text" name="telefono" value="<%= c.getTelefono() %>" class="form-control form-control-sm" style="width:120px;">
        </td>
        <td>
                <a href="PrestamoServlet?nro_cliente=<%= c.getNro_cliente() %>" class="btn btn-primary btn-sm">Préstamos</a>
                <input type="submit" name="action" value="Modificar" class="btn btn-warning btn-sm">
                <a href="ClienteServlet?action=eliminar&id=<%= c.getNro_cliente() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Eliminar cliente?')">Eliminar</a>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
