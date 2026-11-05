package filey.datos;
import java.time.LocalDate;
public class Reserva {
    private final Editorial editorial;
    private final Stand stand;
    private final LocalDate fechaLimiteReserva;
    private double montoPagado;
    public Reserva(Editorial editorial, Stand stand, LocalDate fechaLimiteReserva) {
        this.editorial = editorial; this.stand = stand; this.fechaLimiteReserva = fechaLimiteReserva;
    }
    public Editorial getEditorial() { return editorial; }
    public Stand getStand() { return stand; }
    public LocalDate getFechaLimiteReserva() { return fechaLimiteReserva; }
    public double getMontoPagado() { return montoPagado; }
    public void abonar(double monto) { montoPagado += monto; }
}
