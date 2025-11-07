<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema Cooperativa</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Íconos de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        .btn-chico {
            padding: 6px 16px;
            font-size: 14px;
            width: auto;
            display: inline-block;
            transition: all 0.2s ease-in-out;
        }
        .btn-chico:hover {
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
            background-color: #0d6efd;
            transform: translateY(-2px);
        }
    </style>
</head>
<body class="bg-light">

<div class="container text-center mt-5">
    <div class="card shadow-sm p-3" style="max-width: 400px; margin: auto;">
        <h1 class="mb-3 fs-4">Sistema Cooperativa</h1>
        <p class="mb-3">Bienvenido al sistema de gestión de clientes y préstamos.</p>

        <!-- Botón estilizado con ícono -->
        <a href="ClienteServlet" class="btn btn-primary btn-sm btn-chico mx-auto">
            <i class="bi bi-people-fill me-1"></i> Gestionar Clientes
        </a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
