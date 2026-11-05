package filey.datos;
import java.time.LocalDate;
public class Configuracion {
    private LocalDate fechaProntoPago, fechaLimitePagoTotal, fechaLimiteReserva;
    private int diasAnticipacionAviso = 3;
    private double porcentajeMinimoOcupacion = 0.15;
    public Configuracion(LocalDate prontoPago, LocalDate pagoTotal, LocalDate reserva) {
        this.fechaProntoPago = prontoPago; this.fechaLimitePagoTotal = pagoTotal; this.fechaLimiteReserva = reserva;
    }
    public LocalDate getFechaProntoPago() { return fechaProntoPago; }
    public LocalDate getFechaLimitePagoTotal() { return fechaLimitePagoTotal; }
    public LocalDate getFechaLimiteReserva() { return fechaLimiteReserva; }
    public int getDiasAnticipacionAviso() { return diasAnticipacionAviso; }
    public double getPorcentajeMinimoOcupacion() { return porcentajeMinimoOcupacion; }
    public void setFechaProntoPago(LocalDate fecha) { fechaProntoPago = fecha; }
    public void setFechaLimitePagoTotal(LocalDate fecha) { fechaLimitePagoTotal = fecha; }
    public void setFechaLimiteReserva(LocalDate fecha) { fechaLimiteReserva = fecha; }
    public void setDiasAnticipacionAviso(int dias) { if(dias < 0) throw new IllegalArgumentException(); diasAnticipacionAviso = dias; }
}
