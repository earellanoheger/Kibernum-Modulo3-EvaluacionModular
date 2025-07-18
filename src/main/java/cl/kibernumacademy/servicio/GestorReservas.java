package cl.kibernumacademy.servicio;
import cl.kibernumacademy.modelo.Cancha;
import cl.kibernumacademy.modelo.Reserva;
import java.util.*;


public class GestorReservas {

    // Atributos
    private List<Cancha> canchas;
    private List<Reserva> reservas;

    // Constructor
    public GestorReservas() {
        this.canchas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<Cancha> getCanchas() {
        return List.copyOf(canchas);
    }

    public List<Reserva> getReservas() {
        return List.copyOf(reservas);
    }
    
    // Métodos para registrar canchas, crear reservas, cancelar reservas, etc.
    public void registrarCancha(Cancha cancha) {
        canchas.add(cancha);
    }

    public void crearReserva(Reserva reserva) {
        int idCancha = reserva.getIdCancha();

        reservas.stream()
            // Filtrar reservas existentes para la misma cancha, fecha y hora
            .filter(r -> 
                r.getIdCancha() == idCancha 
                && r.getFecha().equals(reserva.getFecha()) 
                && r.getHora().equals(reserva.getHora())
            )
            // Seleccionar la primera reserva que coincida
            .findFirst()
            // Si existe una reserva, lanzar una excepción
            .ifPresent(r -> {
                throw new IllegalArgumentException("La cancha ya está reservada en este horario");
            });
        
        reservas.add(reserva);

    }

    public void cancelarReserva(Reserva reserva) {

        reservas.stream()
            // Filtrar reservas para encontrar la que coincide con la reserva a cancelar
            .filter(r -> r.getId() == reserva.getId())
            // Encontrar la primera coincidencia
            .findFirst()
            // Si se encuentra, eliminarla de la lista de reservas
            .ifPresent(r -> reservas.remove(r));
        
    }   

    public long calcularReservasPorDia(String fecha){
        return reservas.stream()
            .filter(r -> r.getFecha().equals(fecha))
            .count();
    }

}
