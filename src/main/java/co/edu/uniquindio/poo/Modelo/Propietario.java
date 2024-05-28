package co.edu.uniquindio.poo.Modelo;

public class Propietario {
    private String nombre;
    private String identificacion;

    public Propietario(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }
}
