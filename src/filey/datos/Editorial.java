package filey.datos;
public class Editorial {
    private final int id;
    private final String nombre;
    public Editorial(int id, String nombre) {
        if (id <= 0 || nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Editorial invalida");
        this.id = id; this.nombre = nombre;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
}
