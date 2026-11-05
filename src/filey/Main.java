package filey;
import filey.datos.*;
import filey.negocio.GestorStands;
import filey.notificaciones.NotificadorConsola;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate hoy = LocalDate.now();
        Configuracion config = new Configuracion(hoy.plusDays(2), hoy.plusDays(10), hoy.plusDays(3));
        GestorStands gestor = new GestorStands(config);
        gestor.agregarObservador(new NotificadorConsola());
        Stand stand = new Stand(101, 12, 500);
        gestor.registrarStand(stand);
        Editorial editorial = new Editorial(1, "Editorial Ejemplo");
        gestor.reservar(101, editorial, hoy);
        System.out.println("Al reservar: " + stand.getEstado());
        gestor.revisarNotificaciones(hoy);
        gestor.registrarPago(101, 900, hoy); // 15% de 6000
        System.out.println("Al pagar 15%: " + stand.getEstado());
    }
}
