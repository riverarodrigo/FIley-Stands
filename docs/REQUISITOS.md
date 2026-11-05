# Requisitos funcionales - FILEY

- **RF01.** Registrar stands con identificador numerico, area en m² y precio por m².
- **RF02.** Calcular el precio total de cada stand.
- **RF03.** Consultar el estado de un stand: disponible, reservado, ocupado o bloqueado.
- **RF04.** Bloquear un stand disponible y devolver un stand bloqueado a disponible (administrador).
- **RF05.** Reservar un stand disponible para una editorial con identificador y nombre, dentro de la fecha limite configurable.
- **RF06.** Registrar abonos de una reserva y cambiar el estado a ocupado al alcanzar el 15% del precio total.
- **RF07.** Configurar fecha de pronto pago, fecha limite de pago total, fecha limite de reserva y anticipacion de avisos.
- **RF08.** Notificar por observadores a las editoriales cuando se acerquen el vencimiento de reserva, pronto pago o pago total.
- **RF09.** Liberar reservas sin anticipo suficiente una vez vencido el plazo de reserva.

**Reglas:** un stand reservado se considera ocupado al cubrir al menos el 15%; los stands bloqueados no pueden reservarse; el sistema solo registra pagos en la fecha permitida. RF09 requiere invocar el metodo de revision de vencimientos. No se desarrollo la transferencia opcional de bloqueado a reservado.
