<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5" style="max-width:400px;">
    <h2 class="text-center mb-4">Ingreso al Sistema</h2>

    <form action="LoginServlet" method="post">
        <div class="mb-3">
            <input type="text" name="nombre" class="form-control" placeholder="Usuario" required>
        </div>
        <div class="mb-3">
            <input type="password" name="clave" class="form-control" placeholder="Contraseña" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Ingresar</button>
    </form>

    <!-- Link para cambiar contraseña -->
    <div class="mt-3 text-center">
        <a href="cambiarClave.jsp">Cambiar Contraseña</a>
    </div>

    <%
        if(request.getAttribute("error") != null) {
    %>
        <div class="alert alert-danger mt-2 text-center">
            <%= request.getAttribute("error") %>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
