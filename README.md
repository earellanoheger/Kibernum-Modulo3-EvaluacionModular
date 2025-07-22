# Kibernum-Modulo3-Actividad5

# Testing de un Sistema de Notificaciones Utilizando Mockito

### Módulo 3 - Sesión 5 - Actividad 5

### Equipo 4: 
- Fabiola Díaz
- Felipe Lobos
- Eduardo Arellano
- Carlos Vasquez

## Gestor de Productos con JUnit 

### Actividad 1 : TDD y pruebas unitarias
### RED
Test en zona roja

![RED](img/RED.png)


[Código Zona roja](./src/test/java/cl/kibernumacademy/GestorReservasTestRed.md)

### GREEN
tests en zona verde

![GREEN](img/GREEN.png)

Código fuente en zona verde

[Código Zona verde](./src/main/java/cl/kibernumacademy/servicio/GestorReservasGreen.md)


### REFACTOR
tests

![REFACTOR](img/REFACTOR.png)

código refactorizado
[Código refactor](./src/main/java/cl/kibernumacademy/servicio/GestorReservasRefactor.md)


### Actividad 2 : Principios de diseño
*Principios Solid*
1. **Single responsability Principle**
Cada clase tiene una única responsabilidad bien definida:
Cancha → Representa los datos de una cancha.
Reserva → Modela la información de una reserva.
GestorReservas → Administra la lógica de negocio (crear, cancelar, contar reservas, etc.)

2. **Open/Closed Principle**
Las clases están abiertas a extensión pero cerradas a modificación.
Por ejemplo: **GestorReservas** se puede extender  pero no es necesario modificar su código para agregar nuevas funcionalidades.

3. **Dependency Inversion Principle**
Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones.
**GestorReservas** (módulo de alto nivel) depende de una abstracción (RepositorioReservas) y no de una implementación concreta.

![INTERFAZ](img/INTERFACE.png)

_________________________________________________________

*KISS*
"Hazlo lo más simple posible, pero no más simple."
- Las clases (Cancha, Reserva, GestorReservas) tienen pocos atributos y métodos simples.
- Se usan estructuras directas como List<> en lugar de estructuras complejas innecesarias.
- No se agregan capas ni abstracciones innecesarias desde el principio.

__________________________________________________________

*DRY*
"Evita la duplicación de código."
- Se aplica el principio DRY al reutilizar la lógica para filtrar o buscar reservas mediante expresiones stream(), evitando repetir el mismo código en distintos métodos. También se centraliza la lógica de negocio en GestorReservas

__________________________________________________________

*YAGNI*
"No implementes funcionalidades que no necesitas aún."
- No se anticipan funcionalidades que no han sido requeridas. Esto permite mantener el sistema liviano, enfocado y fácil de mantener.




### Actividad 3 : Uso de mocks
