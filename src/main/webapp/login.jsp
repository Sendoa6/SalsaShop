<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Recuperar mensaje de error si existe
    String error = (String) request.getAttribute("error");
    
    // Recuperar mensaje de éxito (desde registro o logout)
    String success = (String) session.getAttribute("success");
    // También verificar si hay error en sesión
    if (error == null) error = (String) session.getAttribute("error");
    
    // Limpiar variables de sesión para que no salgan dos veces
    if (session.getAttribute("success") != null) session.removeAttribute("success");
    if (session.getAttribute("error") != null) session.removeAttribute("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio sesión - Artupa</title>
    <style type="text/css">
    body { font-family: Arial, sans-serif; background-color: #F6E3DC; margin: 0; padding: 0; height: 100vh; display: flex; justify-content: center; align-items: center; }
    .login-card { background-color: white; width: 100%; max-width: 400px; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
    .card-header { text-align: center; margin-bottom: 20px; }
    .card-header h2 { margin: 0; color: #333; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    .form-control { width: 100%; padding: 10px; font-size: 16px; border: 1px solid #dee2e6; border-radius: 6px; box-sizing: border-box; }
    .input-group { display: flex; align-items: stretch; }
    .input-pass { border-top-right-radius: 0; border-bottom-right-radius: 0; border-right: none; }
    .btn-toggle-pass { background-color: white; border: 1px solid #dee2e6; border-left: none; border-top-right-radius: 6px; border-bottom-right-radius: 6px; cursor: pointer; padding: 0 12px; color: #6c757d; display: flex; align-items: center; justify-content: center; }
    .texto-registro { margin-top: 15px; margin-bottom: 20px; font-size: 14px; }
    .texto-registro a { color: #E94B3C; text-decoration: none; font-weight: bold; }
    .botonIniciarSesion { background-color: #E94B3C; color: white; border: none; padding: 12px; border-radius: 6px; font-size: 18px; font-weight: bold; width: 100%; cursor: pointer; transition: background-color 0.2s; }
    .botonIniciarSesion:hover { background-color: #c23e32; }
    
    .alert { padding: 10px; margin-bottom: 15px; border-radius: 5px; font-size: 14px; text-align: center; }
    .alert-danger { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    .alert-success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
    </style>
</head>
<body>
    <div class="main-container">
        <div class="login-card">
            <div class="card-header">
                <h2>Iniciar sesión</h2>
            </div>
            
            <%-- MENSAJES --%>
            <% if (error != null) { %>
                <div class="alert alert-danger"><%= error %></div>
            <% } %>
            
            <% if (success != null) { %>
                <div class="alert alert-success"><%= success %></div>
            <% } %>

            <form action="SrvIndex" method="post">
                <div class="form-group">
                    <label for="user">Nombre de Usuario:</label>
                    <input type="text" id="user" name="user" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <div class="input-group">
                        <input type="password" id="password" name="password" class="form-control input-pass" required>
                        <button type="button" id="btnVerPass" class="btn-toggle-pass" onclick="togglePassword()">👁️</button>
                    </div>
                </div>
                
                <p class="texto-registro">¿No tienes usuario? <a href="registroUsuario.jsp">Créalo aquí</a></p>
                
                <button type="submit" class="botonIniciarSesion">Iniciar Sesión</button>
            </form>
        </div>
    </div>
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById('password');
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
        }
    </script>
</body>
</html>