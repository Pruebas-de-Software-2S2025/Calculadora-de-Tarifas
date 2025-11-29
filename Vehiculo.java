import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Vehiculo {
    private String idTicket;
    private String placa;
    private String tipoVehiculo;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private String estado;
    
    
    private static final int TARIFA_AUTO = 800;
    private static final int TARIFA_MOTO = 500;
    private static final int TARIFA_CAMIONETA = 1000;

    public Vehiculo(String idTicket, String placa, String tipoVehiculo, LocalDateTime fechaHoraEntrada, String estado) {
        this.idTicket = idTicket;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.estado = estado;
        this.fechaHoraSalida = null;
    }

    public String getIdTicket() { return idTicket; }
    public String getPlaca() { return placa; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public LocalDateTime getFechaHoraEntrada() { return fechaHoraEntrada; }
    public LocalDateTime getFechaHoraSalida() { return fechaHoraSalida; }
    public String getEstado() { return estado; }
    
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
    
    public void registrarSalida() {
        this.estado = "cerrado";
    }
    
    public long calcularTiempoEnMinutos() {
        if(fechaHoraSalida == null) {
            return 0;
        }
        return Duration.between(fechaHoraEntrada, fechaHoraSalida).toMinutes();
    }
    
    public double calcularCobro() {
        if(fechaHoraSalida == null) {
            return 0;
        }
        
        long minutos = calcularTiempoEnMinutos();
        if(minutos == 0) {
            System.out.println("Operación Invalida");
            return 0;
        }
        
        long bloques = (minutos + 29) / 30;
        
        double tarifa = 0;
        switch(tipoVehiculo) {
            case "Auto":
                tarifa = TARIFA_AUTO;
                break;
            case "Moto":
                tarifa = TARIFA_MOTO;
                break;
            case "Camioneta":
                tarifa = TARIFA_CAMIONETA;
                break;
            default:
                break;
        }
        return (bloques * tarifa) < 15000 ? bloques * tarifa : 15000;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String salida = fechaHoraSalida != null ? fechaHoraSalida.format(formato) : "En estacionamiento";
        String cobro = (estado.equals("cerrado")) ? String.format(" | Cobro: $%.0f", calcularCobro()) : "";
        return String.format("ID: %s | Placa: %s | Tipo: %s | Entrada: %s | Salida: %s | Estado: %s%s",
                idTicket, placa, tipoVehiculo, fechaHoraEntrada.format(formato), salida, estado, cobro);
    }
}   