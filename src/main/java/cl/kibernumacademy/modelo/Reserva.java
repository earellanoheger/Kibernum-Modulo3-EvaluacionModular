package cl.kibernumacademy.modelo;


public class Reserva {
    // Atributos
    private static int contador = 0;
    private int id; // No, para pruebas o lógica simple; sí en sistemas reales o con BD
    private String usuario;
    private int idCancha;
    private String fecha;
    private String hora;

    // Constructor
    public Reserva(String usuario, int idCancha, String fecha, String hora) {
        this.id = ++contador; // genera un ID incremental
        this.usuario = usuario;
        this.idCancha = idCancha;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Métodos Getters
    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
