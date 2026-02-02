package artupa.beans;

public class Autor {
    
    private int idAutor;
    private String nombre;

    // Constructor vacío
    public Autor() {
    }

    // Constructor con campos
    public Autor(int idAutor, String nombre) {
        this.idAutor = idAutor;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}