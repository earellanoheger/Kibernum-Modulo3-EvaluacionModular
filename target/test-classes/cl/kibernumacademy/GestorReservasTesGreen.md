```java
package cl.kibernumacademy;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
// Mockito
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.*;

import javax.naming.spi.ResolveResult;
import cl.kibernumacademy.modelo.Cancha;
import cl.kibernumacademy.modelo.Reserva;
import cl.kibernumacademy.servicio.GestorReservas;

@ExtendWith(MockitoExtension.class) 
public class GestorReservasTest {
    private Reserva reserva;
    private Cancha cancha;
    private GestorReservas gestorReservas;
    private List<LocalDateTime> listaHorarios;

    @BeforeEach
    public void setUp() {
        gestorReservas = new GestorReservas();
        listaHorarios = new ArrayList<>(            
            List.of(
                LocalDateTime.of(2025, 1, 1, 8, 0),
                LocalDateTime.of(2025, 1, 1, 9, 0),
                LocalDateTime.of(2025, 1, 1, 10, 0)
            )
        );
        cancha = new Cancha("Cancha 1", "Fútbol", listaHorarios);
    }

    // Prueba 1: Registrar canchas con nombre, tipo de deporte y horarios disponibles
    @Test
    public void testRegistrarCanchas() {
        gestorReservas.registrarCancha(cancha);
        assertTrue(gestorReservas.getCanchas().contains(cancha));
    }

    // Prueba 2: Crear reserva de canchaas
    @Test
    public void testCrearReserva() {
        gestorReservas.registrarCancha(cancha);
        Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        gestorReservas.crearReserva(reserva);
        assertTrue(gestorReservas.getReservas().contains(reserva));
    }

    // Prueba 3: Cancelar una reserva ya creada
    @Test
    public void testCancelarReserva() {
        gestorReservas.registrarCancha(cancha);
        Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        gestorReservas.crearReserva(reserva);
        assertTrue(gestorReservas.getReservas().contains(reserva));
        gestorReservas.cancelarReserva(reserva);
        assertFalse(gestorReservas.getReservas().contains(reserva));
    }

    // Prueba 4: Verificar que una cancha no pueda ser reservada si ya está ocupada
    @Test
    public void testReservaCanchasOcupadas() {
        gestorReservas.registrarCancha(cancha);
        Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        gestorReservas.crearReserva(reserva);
        Reserva reserva2 = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.crearReserva(reserva2);
        });

        assertEquals("La cancha ya está reservada en este horario", exception.getMessage());

        
    }

    // Prueba 5: Calcular numero de reservas por dia
    @Test
    public void testCalcularReservasPorDia() {
        gestorReservas.registrarCancha(cancha);
        gestorReservas.crearReserva(new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00"));
        gestorReservas.crearReserva(new Reserva("Usuario1", cancha.getId(), "2025-01-01","09:00"));
        gestorReservas.crearReserva(new Reserva("Usuario1", cancha.getId(), "2025-01-01","10:00"));

        long reservasDia1 = gestorReservas.calcularReservasPorDia("2025-01-01");
        assertEquals(3,reservasDia1);
        
    }
}
