package filey.negocio;
import filey.datos.*;
import filey.notificaciones.Observador;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GestorStands {
    private final Configuracion config;
    private final Map<Integer, Stand> stands = new HashMap<>();
    private final Map<Integer, Reserva> reservas = new HashMap<>();
    private final List<Observador> observadores = new ArrayList<>();

    public GestorStands(Configuracion config) { this.config = config; }
    public void agregarObservador(Observador o) { observadores.add(o); }
    public void registrarStand(Stand stand) {
        if (stands.putIfAbsent(stand.getId(), stand) != null) throw new IllegalStateException("Stand duplicado");
    }
    public Stand consultarStand(int id) { return buscarStand(id); }
    private Stand buscarStand(int id) {
        Stand s = stands.get(id);
        if (s == null) throw new IllegalArgumentException("Stand inexistente");
        return s;
    }
    public void bloquear(int id) {
        Stand s = buscarStand(id);
        if (s.getEstado() != EstadoStand.DISPONIBLE) throw new IllegalStateException("No disponible");
        s.setEstado(EstadoStand.BLOQUEADO);
    }
    public void desbloquear(int id) {
        Stand s = buscarStand(id);
        if (s.getEstado() != EstadoStand.BLOQUEADO) throw new IllegalStateException("No bloqueado");
        s.setEstado(EstadoStand.DISPONIBLE);
    }
    public Reserva reservar(int id, Editorial editorial, LocalDate hoy) {
        Stand s = buscarStand(id);
        if (s.getEstado() != EstadoStand.DISPONIBLE) throw new IllegalStateException("Stand no disponible");
        if (hoy.isAfter(config.getFechaLimiteReserva())) throw new IllegalStateException("Reservas cerradas");
        Reserva r = new Reserva(editorial, s, config.getFechaLimiteReserva());
        reservas.put(id, r);
        s.setEstado(EstadoStand.RESERVADO);
        return r;
    }
    public void registrarPago(int id, double monto, LocalDate hoy) {
        Reserva r = reservas.get(id);
        if (r == null) throw new IllegalStateException("No existe reserva");
        if (monto <= 0 || !Double.isFinite(monto)) throw new IllegalArgumentException("Monto invalido");
        if (hoy.isAfter(config.getFechaLimitePagoTotal())) throw new IllegalStateException("Pago fuera de plazo");
        double saldo = r.getStand().getPrecioTotal() - r.getMontoPagado();
        if (monto > saldo + 0.000001) throw new IllegalArgumentException("Pago supera saldo");
        r.abonar(monto);
        if (r.getStand().getEstado() == EstadoStand.RESERVADO &&
            r.getMontoPagado() + 0.000001 >= r.getStand().getPrecioTotal() * config.getPorcentajeMinimoOcupacion()) {
            r.getStand().setEstado(EstadoStand.OCUPADO);
        }
    }
    public void revisarNotificaciones(LocalDate hoy) {
        for (Reserva r : reservas.values()) {
            if (r.getStand().getEstado() == EstadoStand.RESERVADO)
                avisarSiCerca(r, hoy, r.getFechaLimiteReserva(), "Tu reserva esta por vencer");
            if (r.getMontoPagado() < r.getStand().getPrecioTotal()) {
                avisarSiCerca(r, hoy, config.getFechaProntoPago(), "Se acerca la fecha de pronto pago");
                avisarSiCerca(r, hoy, config.getFechaLimitePagoTotal(), "Se acerca el limite de pago total");
            }
        }
    }
    private void avisarSiCerca(Reserva r, LocalDate hoy, LocalDate fecha, String mensaje) {
        long dias = ChronoUnit.DAYS.between(hoy, fecha);
        if (dias >= 0 && dias <= config.getDiasAnticipacionAviso())
            for (Observador o : observadores) o.notificar(r.getEditorial(), mensaje + " (" + fecha + ")");
    }
    public void liberarReservasVencidas(LocalDate hoy) {
        Iterator<Map.Entry<Integer, Reserva>> it = reservas.entrySet().iterator();
        while (it.hasNext()) {
            Reserva r = it.next().getValue();
            if (r.getStand().getEstado() == EstadoStand.RESERVADO && hoy.isAfter(r.getFechaLimiteReserva())) {
                r.getStand().setEstado(EstadoStand.DISPONIBLE);
                it.remove();
            }
        }
    }
}
