package cl.kibernumacademy.servicio;

import java.util.List;

import cl.kibernumacademy.modelo.Reserva;

public interface RepositorioReservas {
    void crearReserva(Reserva reserva);
    void cancelarReserva(Reserva reserva);
    List<Reserva> getReservas();
}

