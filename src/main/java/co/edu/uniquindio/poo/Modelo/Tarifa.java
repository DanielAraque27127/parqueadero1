package co.edu.uniquindio.poo.Modelo;

public class Tarifa {

    private double tarifaMotoClasica;
    private double tarifaMotoHibrida;
    private double tarifaCarro;

    public Tarifa(double tarifaMotoClasica, double tarifaMotoHibrida, double tarifaCarro) {
        this.tarifaMotoClasica = tarifaMotoClasica;
        this.tarifaMotoHibrida = tarifaMotoHibrida;
        this.tarifaCarro = tarifaCarro;
    }

    public Tarifa() {
    }

    public double getTarifaMotoClasica() {
        return tarifaMotoClasica;
    }

    public double getTarifaMotoHibrida() {
        return tarifaMotoHibrida;
    }

    public double getTarifaCarro() {
        return tarifaCarro;
    }

    public void setTarifaMotoClasica(double tarifaMotoClasica) {
        this.tarifaMotoClasica = tarifaMotoClasica;
    }

    public void setTarifaMotoHibrida(double tarifaMotoHibrida) {
        this.tarifaMotoHibrida = tarifaMotoHibrida;
    }

    public void setTarifaCarro(double tarifaCarro) {
        this.tarifaCarro = tarifaCarro;
    }

    public void configurarTarifas(double motoClasica, double motoHibrida, double carro) {
        setTarifaMotoClasica(motoClasica);
        setTarifaMotoHibrida(motoHibrida);
        setTarifaCarro(carro);
    }

    public double calcularCosto(Vehiculo vehiculo, long tiempoEstacionado) {
        double tarifa = 0;
        
        if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            tarifa = moto.esHibrida() ? tarifaMotoHibrida : tarifaMotoClasica;
        } else if (vehiculo instanceof Carro) {
            tarifa = tarifaCarro;
        }
        return tarifa * (tiempoEstacionado / 60000.0); // Calcular en minutos
    }
}
