package co.edu.uniquindio.poo.Modelo;

public class Puesto {
    private Vehiculo vehiculo;

    public Puesto() {
        this.vehiculo = null;
    }

    public boolean estaOcupado() {
        return vehiculo != null;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void liberarPuesto() {
        vehiculo = null;
    }

}
