package co.edu.uniquindio.poo.Modelo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*la clase Registro se utiliza para mantener un registro de los vehículos que 
ingresan al parqueadero y la cantidad de dinero que se recauda cada día y cada mes. */
public class Registro {
    private Map<Vehiculo, LocalDateTime> ingresos;
    private Map<String, Double> recaudacionDiaria;
    private Map<String, Double> recaudacionMensual;

/*este bloque de código está creando un nuevo HashMap vacío (inicialización de variable)*/
    public Registro() {
        ingresos = new HashMap<>();
        recaudacionDiaria = new HashMap<>();
        recaudacionMensual = new HashMap<>();
    }
/*el propósito de este método es registrar el ingreso de un vehículo en un puesto 
específico y la hora en que ocurrió este ingreso. */
    public void registrarIngreso(Puesto puesto, Vehiculo vehiculo) {
        ingresos.put(vehiculo, LocalDateTime.now());
    }

    /*se utiliza para obtener el valor actual asociado con el tipo de vehículo en el mapa. Si el tipo de 
    vehículo no está en el mapa, getOrDefault devuelve el valor por defecto proporcionado, que en este caso es 0.0.  */
    
    public void registrarEgreso(Vehiculo vehiculo, double costo) {
        LocalDateTime ingreso = ingresos.remove(vehiculo);
        if (ingreso != null) {
            String tipo = getTipoVehiculo(vehiculo);
            recaudacionDiaria.put(tipo, recaudacionDiaria.getOrDefault(tipo, 0.0) + costo);
            recaudacionMensual.put(tipo, recaudacionMensual.getOrDefault(tipo, 0.0) + costo);
        }
    }
    private String getTipoVehiculo(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            return moto.esHibrida() ? "Moto Híbrida" : "Moto Clásica";
        } else {
            return "Carro";

        }

    }

    public void generarReporteDiario() {
        throw new UnsupportedOperationException("Unimplemented method 'generarReporteDiario'");
    }

    public void generarReporteMensual() {
        throw new UnsupportedOperationException("Unimplemented method 'generarReporteMensual'");
    }
}
