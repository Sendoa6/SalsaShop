<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SalsaShop - Añadir Editorial</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f4f4; display: flex; flex-direction: column; align-items: center; padding: 20px; }
        header { background-color: #2c3e50; color: white; padding: 20px; width: 100%; text-align: center; border-radius: 8px 8px 0 0; max-width: 500px; }
        .form-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 100%; max-width: 500px; box-sizing: border-box; }
        h2 { border-bottom: 2px solid #e74c3c; padding-bottom: 10px; color: #2c3e50; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-guardar { background-color: #e74c3c; color: white; padding: 12px; border: none; border-radius: 5px; width: 100%; cursor: pointer; font-size: 1em; }
        .btn-guardar:hover { background-color: #c0392b; }
        .link-volver { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #7f8c8d; }
    </style>
</head>
<body>
    <header><h1>SalsaShop Admin</h1></header>
    <div class="form-container">
        <h2>Añadir Nueva Editorial</h2>
        <form action="SrvNuevaEditorial" method="post">
            <div class="form-group">
                <label>Nombre de la Editorial:</label>
                <input type="text" name="nombre_editorial" placeholder="Ej: Planeta, Alfaguara..." required>
            </div>
            <button type="submit" class="btn-guardar">Registrar Editorial</button>
            <a href="SrvIndex" class="link-volver">← Volver</a>
        </form>
    </div>
</body>
</html>