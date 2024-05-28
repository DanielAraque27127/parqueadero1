package co.edu.uniquindio.poo.Modelo;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<String, Vehiculo> vehiculosRegistrados = new HashMap<>();
    public static Parqueadero parqueadero;
    private static Registro registro = new Registro();
    private static Map<String, Double> registroDiario = new HashMap<>();
    private static Map<String, Double> registroMensual = new HashMap<>();
    public static Map<Integer, Parqueadero> parqueaderosRegistrados = new HashMap<>();
    private static DecimalFormat decimal = new DecimalFormat("#,###");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    // Crear parqueadero
                    System.out.println("Ingrese el código del parqueadero:");
                    int codigoParqueadero = scanner.nextInt();
                    System.out.println("Ingrese el número de filas del parqueadero:");
                    int filas = scanner.nextInt();
                    System.out.println("Ingrese el número de columnas del parqueadero:");
                    int columnas = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.println("Ingrese la tarifa por minuto para motos CLÁSICAS:");
                    double tarifaMotoClasica = scanner.nextDouble();
                    System.out.println("Ingrese la tarifa por minuto para motos HÍBRIDAS:");
                    double tarifaMotoHibrida = scanner.nextDouble();
                    System.out.println("Ingrese la tarifa por minuto para CARROS:");
                    double tarifaCarro = scanner.nextDouble();
                    scanner.nextLine(); 

                    Parqueadero nuevoParqueadero = new Parqueadero(vehiculosRegistrados, tarifaMotoClasica, tarifaMotoHibrida, tarifaCarro, filas, columnas, codigoParqueadero);
                    parqueaderosRegistrados.put(codigoParqueadero, nuevoParqueadero);
                    parqueadero = nuevoParqueadero; 
                    break;

                case 2:
                    // Registrar vehículo
                    registrarVehiculo(scanner);
                    break;

                case 3:
                    // Ver puestos ocupados y disponibles
                    if (parqueadero != null) {
                        verPuestosOcupadosDisponibles(parqueadero);
                    } else {
                        System.out.println("Debe crear el parqueadero primero.");
                    }
                    break;

                case 4:
                    // Calcular costo de estacionamiento
                    if (!vehiculosRegistrados.isEmpty()) {
                        System.out.println("Ingrese el código del parqueadero:");
                        int codigoParqueaderoCosto = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        calcularCostoEstacionamiento(scanner, codigoParqueaderoCosto);
                    } else {
                        System.out.println("No hay vehículos registrados.");
                    }
                    break;

                case 5:
                    // Generar reporte diario y mensual
                    generarReporte(scanner);
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú Principal");
        System.out.println("");
        System.out.println("1. Crear parqueadero");
        System.out.println("2. Registrar vehículo");
        System.out.println("3. Ver puestos ocupados y disponibles");
        System.out.println("4. Calcular costo de estacionamiento");
        System.out.println("5. Generar reporte diario y mensual");
        System.out.println("6. Salir");
        System.out.println("");
        System.out.print("Ingrese su opción: ");
    }

    private static void registrarVehiculo(Scanner scanner) {
        System.out.println("Ingrese el código del parqueadero:");
        int codigoParqueadero = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Parqueadero parqueadero = obtenerParqueadero(codigoParqueadero);
        if (parqueadero != null) {
            System.out.println("Ingrese la placa del vehículo:");
            String placa = scanner.nextLine();
            System.out.println("Ingrese el modelo del vehículo:");
            String modelo = scanner.nextLine();

            System.out.println("¿Es un carro o una moto? (c/m)");
            String tipo = scanner.nextLine();
            System.out.println("Ingrese el nombre del propietario:");
            String nombrePropietario = scanner.nextLine();
            System.out.println("Ingrese la identificación del propietario:");
            String identificacionPropietario = scanner.nextLine();
            Propietario propietario = new Propietario(nombrePropietario, identificacionPropietario);

            System.out.println("Digite la hora de ingreso (formato militar HH:mm):");
            String horaIngreso = scanner.nextLine();
            double tarifa;
            if (tipo.equalsIgnoreCase("c")) {
                tarifa = parqueadero.getTarifaCarro();
                Carro carro = new Carro(placa, modelo, propietario, tarifa);
                carro.setHoraIngreso(horaIngreso);
                vehiculosRegistrados.put(placa, carro);
            } else {
                System.out.println("Ingrese la velocidad máxima de la moto:");
                double velocidadMaxima = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("¿La moto es híbrida? (true/false)");
                boolean esHibrida = scanner.nextBoolean();
                scanner.nextLine(); 
                tarifa = esHibrida ? parqueadero.getTarifaMotoHibrida() : parqueadero.getTarifaMotoClasica();
                Moto moto = new Moto(placa, modelo, propietario, tarifa, velocidadMaxima, esHibrida);
                moto.setHoraIngreso(horaIngreso);
                vehiculosRegistrados.put(placa, moto);
            }
            System.out.println("Vehículo registrado correctamente.");
        } else {
            System.out.println("No se encontró un parqueadero con el código " + codigoParqueadero);
        }
    }

    private static void verPuestosOcupadosDisponibles(Parqueadero parqueadero) {
        Puesto[][] puestos = parqueadero.getPuestos();
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                Puesto puesto = puestos[i][j];
                if (puesto.estaOcupado()) {
                    Vehiculo vehiculo = puesto.getVehiculo();
                    System.out.println("El puesto (" + i + ", " + j + ") está ocupado por un vehículo con placa " + vehiculo.getPlaca());
                } else {
                    System.out.println("El puesto (" + i + ", " + j + ") está disponible");
                }
            }
        }
    }

    private static void calcularCostoEstacionamiento(Scanner scanner, int codigoParqueadero) {
        System.out.println("Ingrese la placa del vehículo para calcular el costo:");
        String placa = scanner.nextLine();
        Vehiculo vehiculo = vehiculosRegistrados.get(placa);

        if (vehiculo != null) {
            System.out.println("Digite la hora de salida (en formato militar HH:mm):");
            String horaSalida = scanner.nextLine();

            long tiempoEstacionado = calcularTiempoEstacionado(horaSalida, vehiculo.getHoraIngreso());
            double costo = obtenerParqueadero(codigoParqueadero).calcularCosto(vehiculo, tiempoEstacionado);
            System.out.printf("El costo de estacionamiento para el vehículo %s (%s) durante %d minutos es: $%s%n", vehiculo.getPlaca(), vehiculo.getModelo(), tiempoEstacionado, decimal.format(costo));
            registro.registrarEgreso(vehiculo, costo);
            registrarMovimientoDiarioMensual(costo);
            liberarPuesto(vehiculo, obtenerParqueadero(codigoParqueadero));
        } else {
            System.out.println("No se encontró el vehículo con la placa " + placa);
        }
    }

    public static long calcularTiempoEstacionado(String horaSalida, String horaIngreso) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date fechaSalida = format.parse(horaSalida);
            Date fechaIngreso = format.parse(horaIngreso);
            long tiempoEstacionadoEnMinutos = (fechaSalida.getTime() - fechaIngreso.getTime()) / 60000;
            return tiempoEstacionadoEnMinutos;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void registrarMovimientoDiarioMensual(double costo) {
        Date fechaActual = new Date();
        SimpleDateFormat formatoDiario = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoMensual = new SimpleDateFormat("MM/yyyy");

        String fechaDiario = formatoDiario.format(fechaActual);
        String fechaMensual = formatoMensual.format(fechaActual);

        registroDiario.put(fechaDiario, registroDiario.getOrDefault(fechaDiario, 0.0) + costo);
        registroMensual.put(fechaMensual, registroMensual.getOrDefault(fechaMensual, 0.0) + costo);
    }

    private static void generarReporte(Scanner scanner) {
        System.out.println("Ingrese el código del parqueadero para generar el reporte:");
        int codigoParqueadero = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Parqueadero parqueadero = obtenerParqueadero(codigoParqueadero);
        if (parqueadero != null) {
            System.out.println("Reporte Diario:");
            for (Map.Entry<String, Double> entry : registroDiario.entrySet()) {
                System.out.println("Fecha: " + entry.getKey() + ", Total Recaudado: $" + decimal.format(entry.getValue()));
            }

            System.out.println("\nReporte Mensual:");
            for (Map.Entry<String, Double> entry : registroMensual.entrySet()) {
                System.out.println("Mes: " + entry.getKey() + ", Total Recaudado: $" + decimal.format(entry.getValue()));
            }
        } else {
            System.out.println("No se encontró un parqueadero con el código " + codigoParqueadero);
        }
    }

    private static void liberarPuesto(Vehiculo vehiculo, Parqueadero parqueadero) {
        Puesto[][] puestos = parqueadero.getPuestos();
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                Puesto puesto = puestos[i][j];
                if (puesto.getVehiculo() != null && puesto.getVehiculo().equals(vehiculo)) {
                    puesto.setVehiculo(null);
                    System.out.println("El vehículo con placa " + vehiculo.getPlaca() + " ha sido retirado del puesto (" + i + ", " + j + ")");
                    return;
                }
            }
        }
    }

    public static Parqueadero obtenerParqueadero(int codigoParqueadero) {
        Parqueadero parqueadero = parqueaderosRegistrados.get(codigoParqueadero);
        if (parqueadero == null) {
            System.out.println("No se encontró un parqueadero con el código " + codigoParqueadero);
        }
        return parqueadero;
    }
}