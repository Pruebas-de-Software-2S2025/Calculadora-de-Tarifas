import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Vehiculo> vehiculos = new ArrayList<>();
        int contadorTicket = 1;
        System.out.print("Calculadora de Tarifas");
        
        int opcion = 0;
        while(opcion != 7){
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar entrada de vehículo");
            System.out.println("2. Registrar salida de vehículo (calcular cobro)");
            System.out.println("3. Listar tickets abiertos");
            System.out.println("4. Listar tickets cerrados");
            System.out.println("5. Mostrar detalle de un ticket");
            System.out.println("6. Mostrar total recaudado del día");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del vehículo: ");
                    String placa = entrada.nextLine();
                    System.out.print("Seleccione una opción (1=Auto, 2=Moto, 3=Camioneta): ");
                    int tipo = entrada.nextInt();
                    entrada.nextLine();
                    String tipoVeh = (tipo == 1) ? "Auto" : (tipo == 2) ? "Moto" : "Camioneta";
                    Vehiculo v = new Vehiculo("TK-" + contadorTicket, placa, tipoVeh, LocalDateTime.now(), "En estacionamiento");
                    vehiculos.add(v);
                    contadorTicket++;
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    break;
            }
        }
        
        entrada.close();
        System.out.println("¡Hasta luego!");
    }
}
