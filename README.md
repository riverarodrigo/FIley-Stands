# FILEY - Administracion de stands (Java)

Proyecto escolar basico de backend en memoria para administrar stands de FILEY.

## Contenido
- `src/filey/datos`: Stand, Editorial, Reserva, Configuracion y estados.
- `src/filey/negocio`: registro, bloqueo, reservas, pagos y verificacion de vencimientos.
- `src/filey/notificaciones`: observadores y avisos en consola.
- `docs/REQUISITOS.md`: requisitos funcionales.
- `docs/DIAGRAMA_CLASES.puml` y `docs/DIAGRAMA_ESTADOS.puml`: diagramas PlantUML.

## Ejecutar (Java 11 o superior)
Desde la carpeta del proyecto:

**Git Bash / Linux / macOS:**
```bash
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out filey.Main
```

**PowerShell (Windows):**
```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out filey.Main
```

## Notas
- Ejemplo de clases y logica sin base de datos ni interfaz grafica.
- Los pagos se representan con `double` por sencillez escolar.
- Los avisos son por consola: se ejecuta manualmente `revisarNotificaciones(fecha)`.
- La fecha de reserva se configura como fecha global de cierre.
- La transferencia directa desde bloqueado se omite porque era opcional.
