package filey.notificaciones;
import filey.datos.Editorial;
public class NotificadorConsola implements Observador {
    @Override public void notificar(Editorial editorial, String mensaje) {
        System.out.println("Aviso a " + editorial.getNombre() + ": " + mensaje);
    }
}
