import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehiculo {
    private String idTicket;
    private String placa;
    private String tipoVehiculo;
    private LocalDateTime fechaHoraEntrada;
    private String estado;

    public Vehiculo(String idTicket, String placa, String tipoVehiculo, LocalDateTime fechaHoraEntrada, String estado) {
        this.idTicket = idTicket;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.estado = estado;
    }

    public String getIdTicket() { return idTicket; }
    public String getPlaca() { return placa; }
    public String getTipoVehiculo() { return tipoVehiculo; }
    public LocalDateTime getFechaHoraEntrada() { return fechaHoraEntrada; }
    public String getEstado() { return estado; }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("ID: %s | Placa: %s | Tipo: %s | Entrada: %s | Estado: %s",
                idTicket, placa, tipoVehiculo, fechaHoraEntrada.format(formato), "En estacionamiento", estado);
    }
}   