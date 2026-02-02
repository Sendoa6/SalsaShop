package artupa.beans;

public class Libro {
    
    // Atributos que coinciden con tu tabla 'libros' de la base de datos
    private String isbn;
    private String titulo;
    private double precio;
    private int stock;
    private int idAutor;
    private int idEditorial;
    private int idCategoria;
    private String nombreAutor;
    
    // Constructor vacío (necesario)
    public Libro() {
    }

    // Constructor con todos los campos (opcional, pero útil)
    public Libro(String isbn, String titulo, double precio, int stock, int idAutor, int idEditorial, int idCategoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
        this.idAutor = idAutor;
        this.idEditorial = idEditorial;
        this.idCategoria = idCategoria;
    }

    // GETTERS Y SETTERS (Imprescindibles para que el JSP pueda leer los datos)
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNombreAutor() {
        return nombreAutor;
    }
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    
    // Método auxiliar para mostrar el precio bonito (opcional)
    // Esto te ayuda si quieres imprimirlo formateado en algún log
    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", precio=" + precio + "]";
    }
}