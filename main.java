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
                    Vehiculo v = new Vehiculo("TK-" + contadorTicket, placa, tipoVeh, LocalDateTime.now(), "abierto");
                    vehiculos.add(v);
                    contadorTicket++;
                    break;
                case 2:
                    System.out.print("\nTicket que se va a cerrar: ");
                    String idCerrar = entrada.nextLine();
                    boolean encontrado = false;
                    for(Vehiculo veh : vehiculos) {
                        if(veh.getIdTicket().equals(idCerrar) && veh.getEstado().equals("abierto")) {
                            veh.registrarSalida();
                            System.out.println("Vehículo cerrado: " + veh.getPlaca());
                            System.out.println(veh);
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado) {
                        System.out.println("No existe el ticket o y está cerrado");
                    }
                    break;
                case 3:
                    System.out.println("\n--- TICKETS ABIERTOS ---");
                    for(Vehiculo veh : vehiculos) {
                        if(veh.getEstado().equals("abierto")) {
                            System.out.println(veh);
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n--- TICKETS CERRADOS ---");
                    for(Vehiculo veh : vehiculos) {
                        if(veh.getEstado().equals("cerrado")) {
                            System.out.println(veh);
                        }
                    }
                    break;
                case 5:
                    System.out.print("\nIngrese el ID del ticket (ej: TK-1): ");
                    String idBuscar = entrada.nextLine();
                    boolean encontradoDetalle = false;
                    for(Vehiculo veh : vehiculos) {
                        if(veh.getIdTicket().equals(idBuscar)) {
                            System.out.println("\n--- DETALLE DEL TICKET ---");
                            System.out.println(veh);
                            encontradoDetalle = true;
                            break;
                        }
                    }
                    if(!encontradoDetalle) {
                        System.out.println("Ticket no encontrado");
                    }
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
