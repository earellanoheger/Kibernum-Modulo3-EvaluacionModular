package cl.kibernumacademy.servicio;
import cl.kibernumacademy.modelo.Cancha;
import cl.kibernumacademy.modelo.Reserva;
import java.util.*;


public class GestorReservas{

    private final RepositorioReservas repositorio;
    private List<Cancha> canchas;

    public GestorReservas(RepositorioReservas repositorio) {
        this.repositorio = repositorio;
        this.canchas = new ArrayList<>();
    }

    // Atributos

    //private List<Reserva> reservas;

    // Constructor
    /*public GestorReservas() {
        this.canchas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }*/

    public List<Cancha> getCanchas() {
        return List.copyOf(canchas);
    }

    public List<Reserva> getReservas() {
        //return List.copyOf(reservas);
        return repositorio.obtener();
    }
    
    // Métodos para registrar canchas, crear reservas, cancelar reservas, etc.
    public boolean registrarCancha(Cancha cancha) {

        if (cancha==null ||cancha.getNombre() == null  || cancha.getTipoDeporte() == null) {
            throw new IllegalArgumentException("Nombre o tipo de deporte son valores invalidos");
        }
        canchas.add(cancha);
        return true;
    }

    public void crearReserva(Reserva reserva) {
        int idCancha = reserva.getIdCancha();

        repositorio.obtener().stream()
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
        
        //reservas.add(reserva);
        repositorio.guardar(reserva);
        
    }

    public void cancelarReserva(Reserva reserva) {

        repositorio.obtener().stream()
            // Filtrar reservas para encontrar la que coincide con la reserva a cancelar
            .filter(r -> r.getId() == reserva.getId())
            // Encontrar la primera coincidencia
            .findFirst()
            // Si se encuentra, eliminarla de la lista de reservas
            .ifPresent(r -> repositorio.eliminar(reserva));
           // repositorio.eliminar(reserva);
            
    }   

    public long calcularReservasPorDia(String fecha){
        return repositorio.obtener().stream()
            .filter(r -> r.getFecha().equals(fecha))
            .count();
    }

}
