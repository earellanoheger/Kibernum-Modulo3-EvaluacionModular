```java
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
        return canchas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    // Métodos para registrar canchas, crear reservas, cancelar reservas, etc.
    public void registrarCancha(Cancha cancha) {
        canchas.add(cancha);
    }

    public void crearReserva(Reserva reserva) {
        int idCancha = reserva.getIdCancha();
        
        for (Reserva r : reservas) {
            if (r.getIdCancha() == idCancha && r.getFecha().equals(reserva.getFecha()) && r.getHora().equals(reserva.getHora())) {
                throw new IllegalArgumentException("La cancha ya está reservada en este horario");
            }
        }
        
        reservas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        for (Reserva r : reservas) {
            if (r.getId() == reserva.getId()) {
                reservas.remove(r);
                return; // Salir del método una vez que se ha cancelado la reserva
            }            
        }
    }   

    public long calcularReservasPorDia(String fecha){
        return reservas.stream()
            .filter(r -> r.getFecha().equals(fecha))
            .count();
    }

}
