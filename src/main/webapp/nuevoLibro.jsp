<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SalsaShop - Añadir Libro</title>
    <style>
        /* Estilos integrados para que coincida con la estética de la tienda */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            width: 100%;
            text-align: center;
            border-radius: 8px 8px 0 0;
            margin-bottom: 20px;
            max-width: 600px;
        }

        .form-container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 600px;
            box-sizing: border-box;
        }

        h2 {
            margin-top: 0;
            color: #2c3e50;
            border-bottom: 2px solid #e74c3c; /* Rojo Salsa */
            padding-bottom: 10px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            font-size: 0.9em;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Evita que el input se salga del contenedor */
        }

        .grid-ids {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 10px;
        }

        .btn-container {
            text-align: center;
            margin-top: 25px;
        }

        .btn-guardar {
            background-color: #e74c3c; /* Rojo Salsa */
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
        }

        .btn-guardar:hover {
            background-color: #c0392b;
        }

        .link-volver {
            display: block;
            margin-top: 15px;
            text-decoration: none;
            color: #7f8c8d;
            font-size: 0.9em;
        }

        .link-volver:hover {
            color: #2c3e50;
        }
    </style>
</head>
<body>

    <header>
        <h1>SalsaShop Admin</h1>
    </header>

    <div class="form-container">
        <h2>Añadir Nuevo Libro</h2>
        
        <form action="SrvNuevoLibro" method="post">
            <div class="form-group">
                <label>ISBN (13 dígitos):</label>
                <input type="text" name="isbn" placeholder="Ej: 9788412345678" maxlength="13" required>
            </div>

            <div class="form-group">
                <label>Título del Libro:</label>
                <input type="text" name="titulo" maxlength="50" required>
            </div>

            <div class="form-group">
                <label>Precio (€):</label>
                <input type="number" step="0.01" name="precio" required>
            </div>

            <div class="form-group">
                <label>Stock disponible:</label>
                <input type="number" name="stock" required>
            </div>

            <div class="grid-ids">
                <div class="form-group">
                    <label>ID Autor:</label>
                    <input type="number" name="id_autor" required>
                </div>
                <div class="form-group">
                    <label>ID Edit.:</label>
                    <input type="number" name="id_editorial" required>
                </div>
                <div class="form-group">
                    <label>ID Cat.:</label>
                    <input type="number" name="id_categoria" required>
                </div>
            </div>

            <div class="btn-container">
                <button type="submit" class="btn-guardar">Registrar Libro</button>
                <a href="SrvIndex" class="link-volver">← Volver a la tienda</a>
            </div>
        </form>
    </div>

</body>
</html>