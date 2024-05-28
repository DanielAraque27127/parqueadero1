package co.edu.uniquindio.poo.Modelo;


public class Moto extends Vehiculo {
    private double velocidadMaxima;
    private boolean esHibrida;

    public Moto(String placa, String modelo, Propietario propietario, double tarifa, double velocidadMaxima, boolean esHibrida) {
        super(placa, modelo, propietario, tarifa);
        this.esHibrida = esHibrida;
        this.velocidadMaxima = velocidadMaxima;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public boolean esHibrida() {
        return esHibrida;
    }
}
