package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.Modelo.*;

import java.util.HashMap;
import java.util.logging.Logger;

import org.testng.annotations.Test;

public class AppTest {
    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    private Parqueadero parqueadero;
    public void setUp() {
        parqueadero = new Parqueadero(new HashMap<>(), 50.0, 75.0, 100.0, 3, 3, 1);
        Main.parqueaderosRegistrados.put(1, parqueadero);
        Main.parqueadero = parqueadero;
    }

    @Test
    public void crearParqueadero() {
        LOG.info("Iniciado test crearParqueadero");

        Parqueadero parqueadero = Main.obtenerParqueadero(1);
        assertNotNull(parqueadero);
        assertEquals(1, parqueadero.getCodigoParqueadero());

        LOG.info("Finalizando test crearParqueadero");
    }

    private void assertEquals(int i, int j) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    private void assertNotNull(Parqueadero parqueadero2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
    }
    @Test
    public void calcularCostoEstacionamiento() {
        LOG.info("Iniciado test calcularCostoEstacionamiento");

        Propietario propietario = new Propietario("Carlos Ruiz", "789012");
        Carro carro = new Carro("DEF456", "Honda", propietario, 100.0);
        carro.setHoraIngreso("10:00");
        Main.vehiculosRegistrados.put("DEF456", carro);

        int tiempoEstacionado = (int) Main.calcularTiempoEstacionado("12:00", carro.getHoraIngreso());
        double costo = parqueadero.calcularCosto(carro, tiempoEstacionado);

        assertEquals(120, tiempoEstacionado); // 2 horas en minutos
        assertEquals(12000.0, costo); // 120 minutos * 100.0 por minuto

        LOG.info("Finalizando test calcularCostoEstacionamiento");
    }

    private void assertEquals(double d, double costo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }
}

