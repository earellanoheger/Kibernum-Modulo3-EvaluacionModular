package cl.kibernumacademy.servicio;

import java.util.List;

import cl.kibernumacademy.modelo.Reserva;

public interface RepositorioReservas {
    boolean guardar(Reserva reserva);
    boolean eliminar(Reserva reserva);
    List<Reserva> obtener();
}

