package cl.kibernumacademy.modelo;

import java.util.List;
import java.time.LocalDateTime;

public class Cancha {
    // Atributo id con contador
    private static int contador = 0;
    private int id;
    // Atributos de la cancha
    private String nombre;
    private String tipoDeporte;
    private List<LocalDateTime> horariosDisponibles;

    // Constructor
    public Cancha(String nombre, String tipoDeporte, List<LocalDateTime> horariosDisponibles) {
        this.id = ++contador;
        this.nombre = nombre;
        this.tipoDeporte = tipoDeporte;
        this.horariosDisponibles = horariosDisponibles;
    }

    // Getters
    public int getId() {
        return id;
    }   

    public String getNombre() {
        return nombre;
    }

    public String getTipoDeporte() {
        return tipoDeporte;
    }

    public List<LocalDateTime> getHorariosDisponibles() {
        return horariosDisponibles;
    }
    
}
