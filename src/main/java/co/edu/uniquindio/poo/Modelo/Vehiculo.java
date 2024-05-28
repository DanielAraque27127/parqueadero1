package co.edu.uniquindio.poo.Modelo;

public class Vehiculo {
    private String placa;
    private String modelo;
    public Propietario propietario;
    private double tarifa;
    private String horaIngreso;

    public Vehiculo(String placa, String modelo, Propietario propietario, double tarifa) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.tarifa = tarifa;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}