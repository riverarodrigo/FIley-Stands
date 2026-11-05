package filey.datos;
public class Stand {
    private final int id;
    private final double metrosCuadrados;
    private final double precioPorMetro;
    private EstadoStand estado = EstadoStand.DISPONIBLE;
    public Stand(int id, double metrosCuadrados, double precioPorMetro) {
        if (id <= 0 || metrosCuadrados <= 0 || precioPorMetro <= 0) throw new IllegalArgumentException("Datos invalidos");
        this.id = id; this.metrosCuadrados = metrosCuadrados; this.precioPorMetro = precioPorMetro;
    }
    public int getId() { return id; }
    public double getPrecioTotal() { return metrosCuadrados * precioPorMetro; }
    public double getMetrosCuadrados() { return metrosCuadrados; }
    public double getPrecioPorMetro() { return precioPorMetro; }
    public EstadoStand getEstado() { return estado; }
    public void setEstado(EstadoStand estado) { this.estado = estado; }
}
