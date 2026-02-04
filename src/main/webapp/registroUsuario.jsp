<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse - Artupa</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        /* Estilos generales */
        body {
            background-color: #F6E3DC; /* Fondo Beige */
            font-family: Arial, sans-serif;
        }
        .card {
            border: none;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            border-radius: 12px;
            background-color: white;
        }
        .form-control {
            margin-bottom: 10px;
        }
        /* Clase para forzar visualmente minúsculas */
        .minusculas {
            text-transform: lowercase;
        }
        /* Estilo del botón rojo */
        .botonRegistroSesion {
            background-color: #E94B3C;
            color: white;
            border: none;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .botonRegistroSesion:hover {
            background-color: #c23e32;
        }
        /* Estilo de los enlaces */
        a {
            color: #E94B3C;
            text-decoration: none;
            font-weight: bold;
        }
        
        a:hover {
            text-decoration: underline;
            color: #c23e32;
        }
        /* Ajuste para los botones del ojo dentro del input */
        .input-group .btn {
            border: 1px solid #dee2e6;
            border-left: none;
        }
        .input-group .btn:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="container d-flex justify-content-center my-5">
        <div class="card p-4" style="max-width: 500px; width: 100%;"> 
            <div class="d-flex justify-content-center align-items-center m-3">
                <h2>Registro de Cliente</h2>
            </div>
            
            <%-- Gestión de errores de sesión Java --%>
            <%
                String error = (String) session.getAttribute("error");
                String success = (String) session.getAttribute("success");
                if (error != null) {
            %>
                <div class="alert alert-danger" role="alert">
                    <%= error %>
                </div>
            <%
                    session.removeAttribute("error");
                }
                if (success != null) {
            %>
                <div class="alert alert-success" role="alert">
                    <%= success %>
                </div>
            <%
                    session.removeAttribute("success");
                }
            %>
            <form action="SrvRegistroUsuario" method="post" name="formRegistro">
                
                <div class="mb-3">
                    <label class="form-label">DNI:</label>
                    <input type="text" name="dni" class="form-control" placeholder="12345678A" required>
                </div>
                
				<div class="mb-3">
				    <label class="form-label">Nombre:</label>
				    <input type="text" name="nombre" class="form-control" required>
				</div>
				
				<div class="row">
				    <div class="col-6">
				        <label class="form-label">Primer Apellido:</label>
				        <input type="text" name="apellido1" class="form-control" required>
				    </div>
				    <div class="col-6">
				        <label class="form-label">Segundo Apellido:</label>
				        <input type="text" name="apellido2" class="form-control"> </div>
				</div>

                <div class="mb-3">
                    <label class="form-label">Fecha de Nacimiento:</label>
                    <input type="date" name="fechaNacimiento" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Dirección:</label>
                    <input type="text" name="direccion" class="form-control">
                </div>
                
                <div class="mb-3">
                    <label for="usuario" class="form-label">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" class="form-control minusculas" placeholder="tu.usuario" required>
                </div>
                
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="correo@ejemplo.com" required>
                </div>
                
                <div class="mb-3">
                    <label for="contraseña" class="form-label">Contraseña:</label>
                    <div class="input-group">
                        <input type="password" id="contraseña" name="password" class="form-control border-end-0" required>
                        <button class="btn bg-white text-secondary" type="button" id="btnVerPass">
                             <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z"/>
                                <path d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z"/>
                            </svg> 
                        </button>
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="repetirContraseña" class="form-label">Repetir contraseña:</label>
                    <div class="input-group">
                        <input type="password" id="repetirContraseña" name="repetirContraseña" class="form-control border-end-0" required>
                        <button class="btn bg-white text-secondary" type="button" id="btnVerPass2">
                             <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z"/>
                                <path d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z"/>
                            </svg>
                        </button>
                    </div>
                </div>
                <button id="registrarse" type="submit" class="botonRegistroSesion w-100">Registrarse</button>
                
                <p class="mt-3 text-center">¿Ya tienes usuario? <a href="login.jsp">Inicia sesión</a></p>
            </form>
        </div>
    </div>
    <script>
        // Lógica mostrar/ocultar contraseñas
        document.getElementById('btnVerPass').addEventListener('click', function() {
            const input = document.getElementById('contraseña');
            input.type = (input.type === "password") ? "text" : "password";
        });
        
        document.getElementById('btnVerPass2').addEventListener('click', function() {
            const input = document.getElementById('repetirContraseña');
            input.type = (input.type === "password") ? "text" : "password";
        });
    </script>
</body>
</html>