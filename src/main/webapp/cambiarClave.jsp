<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cambiar Contraseña</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5" style="max-width:400px;">
    <h2 class="text-center mb-4">Cambiar Contraseña</h2>

    <form action="CambiarClaveServlet" method="post">
        <div class="mb-3">
            <input type="text" name="usuario" class="form-control" placeholder="Usuario" required>
        </div>
        <div class="mb-3">
            <input type="password" name="claveActual" class="form-control" placeholder="Clave Actual" required>
        </div>
        <div class="mb-3">
            <input type="password" name="nuevaClave" class="form-control" placeholder="Nueva Clave" required>
        </div>
        <button type="submit" class="btn btn-success w-100">Cambiar Contraseña</button>
    </form>

    <div class="mt-3 text-center">
        <a href="login.jsp">Volver al Login</a>
    </div>

    <%
        if(request.getAttribute("ok") != null) {
    %>
        <div class="alert alert-success mt-2 text-center">
            <%= request.getAttribute("ok") %>
        </div>
    <%
        } else if(request.getAttribute("error") != null) {
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

