package cl.kibernumacademy;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import cl.kibernumacademy.servicio.RepositorioReservas;

@ExtendWith(MockitoExtension.class) 
public class GestorReservasTest {
    private Reserva reserva;
    private Cancha cancha;
    
    private List<LocalDateTime> listaHorarios;

    @Mock
    private RepositorioReservas repositorioMock;
    
    @Captor // Permite capturar argumentos pasados a métodos mockeados
    private ArgumentCaptor<Reserva> reservaCaptor;

    @InjectMocks
    private GestorReservas gestorReservas;
    
    @BeforeEach
    public void setUp() {
        gestorReservas = new GestorReservas(repositorioMock);
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

    // Prueba 2: Crear reserva de canchaas CON MOCKS
    @Test
    public void testCrearReserva() {
        // gestorReservas.registrarCancha(cancha);
        // Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        // gestorReservas.crearReserva(reserva);
        // assertTrue(gestorReservas.getReservas().contains(reserva));
      // Given
        given(repositorioMock.obtener()).willReturn(List.of());

        Reserva nuevaReserva = new Reserva("Pepe", cancha.getId(), "2025-01-01", "10:00");

        // When
        gestorReservas.crearReserva(nuevaReserva);

        // Then
        verify(repositorioMock).guardar(reservaCaptor.capture());

        Reserva capturada = reservaCaptor.getValue();
        assertEquals("Pepe", capturada.getUsuario());
        assertEquals("10:00", capturada.getHora());
    }

    // Prueba 3: Cancelar una reserva ya creada
    @Test
    public void testCancelarReserva() {
        /*gestorReservas.registrarCancha(cancha);
        Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        gestorReservas.crearReserva(reserva);
        assertTrue(gestorReservas.getReservas().contains(reserva));
        gestorReservas.cancelarReserva(reserva);
        assertFalse(gestorReservas.getReservas().contains(reserva));
    */

        Reserva reserva = new Reserva("Ana", cancha.getId(), "2025-01-01", "09:00");
        given(repositorioMock.eliminar(reserva)).willReturn(true);
        gestorReservas.cancelarReserva(reserva);

        verify(repositorioMock).eliminar(reserva);
    }

    // Prueba 4: Verificar que una cancha no pueda ser reservada si ya está ocupada
    @Test
    public void testReservaCanchasOcupadas() {
       /* gestorReservas.registrarCancha(cancha);
        Reserva reserva = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        gestorReservas.crearReserva(reserva);
        Reserva reserva2 = new Reserva("Usuario1", cancha.getId(), "2025-01-01","08:00");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestorReservas.crearReserva(reserva2);
        });

        assertEquals("La cancha ya está reservada en este horario", exception.getMessage());
        */
       // Given: ya hay una reserva para la misma cancha, fecha y hora
        Reserva existente = new Reserva("Juan", cancha.getId(), "2025-01-01", "10:00");
        given(repositorioMock.obtener()).willReturn(List.of(existente));

        Reserva nueva = new Reserva("Pedro", cancha.getId(), "2025-01-01", "10:00");

        // Then
        assertThrows(IllegalArgumentException.class, () -> gestorReservas.crearReserva(nueva));
        verify(repositorioMock, never()).guardar(any());

    
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
