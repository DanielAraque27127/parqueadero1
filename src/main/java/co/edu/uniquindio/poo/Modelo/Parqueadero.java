package co.edu.uniquindio.poo.Modelo;

import java.util.Map;

public class Parqueadero extends Tarifa {
    private Puesto[][] puestos;
    private double tarifaMotoClasica;
    private double tarifaMotoHibrida;
    private double tarifaCarro;
    private Map<String, Vehiculo> vehiculosRegistrados;
    private int codigoParqueadero;

    public Parqueadero(Map<String, Vehiculo> vehiculosRegistrados, double tarifaMotoClasica, double tarifaMotoHibrida, double tarifaCarro, int filas, int columnas, int codigoParqueadero) {
        this.vehiculosRegistrados = vehiculosRegistrados;
        this.tarifaMotoClasica = tarifaMotoClasica;
        this.tarifaMotoHibrida = tarifaMotoHibrida;
        this.tarifaCarro = tarifaCarro;
        this.codigoParqueadero= codigoParqueadero;
        inicializarPuestos(filas, columnas);
    }



    private void inicializarPuestos(int filas, int columnas) {
        puestos = new Puesto[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puestos[i][j] = new Puesto();
            }
        }
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

    public boolean ubicarVehiculo(Vehiculo vehiculo, int fila, int columna) {
        if (fila >= 0 && fila < puestos.length && columna >= 0 && columna < puestos[0].length) {
            if (!puestos[fila][columna].estaOcupado()) {
                puestos[fila][columna].setVehiculo(vehiculo);
                return true;
            }
        }
        return false;
    }

    public Puesto[][] getPuestos() {
        return puestos;
    }

    public void setPuestos(Puesto[][] puestos) {
        this.puestos = puestos;
    }

    public double calcularCosto(Vehiculo vehiculo, long tiempoEstacionado) {
        double tarifaVehiculo = 0;
        
        // Determinar la tarifa según el tipo de vehículo
        if (vehiculo instanceof Carro) {
            tarifaVehiculo = tarifaCarro;
        } else if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            if (moto.esHibrida()) {
                tarifaVehiculo = tarifaMotoHibrida;
            } else {
                tarifaVehiculo = tarifaMotoClasica;
            }
        }
        
        // Calcular el costo multiplicando la tarifa por el tiempo de estacionamiento
        return tiempoEstacionado * tarifaVehiculo;
    }
    
    public Vehiculo getVehiculoPorPlaca(String placa) {
        return vehiculosRegistrados.get(placa);
    }



    public int getCodigoParqueadero() {
        return codigoParqueadero;
    }
}
