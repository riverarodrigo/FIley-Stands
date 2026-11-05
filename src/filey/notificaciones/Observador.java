package filey.notificaciones;
import filey.datos.Editorial;
public interface Observador {
    void notificar(Editorial editorial, String mensaje);
}
