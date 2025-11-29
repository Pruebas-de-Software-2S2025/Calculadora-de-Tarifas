
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {
    private final LocalDateTime fechaEntrada = LocalDateTime.of(2024, 1, 1, 10, 0); //base
    @Test
    @DisplayName("Debe inicializar correctamente un vehículo")
    void testConstructor() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");

        assertAll("Propiedades del Vehículo",
            () -> assertEquals("TK-1", v.getIdTicket()),
            () -> assertEquals("Auto", v.getTipoVehiculo()),
            () -> assertEquals("abierto", v.getEstado()),
            () -> assertNull(v.getFechaHoraSalida(), "La fecha de salida debería ser null al inicio")
        );
    }

    @Test
    @DisplayName("Cálculo Auto: 35 min deberían ser 2 bloques (30+5) -> $1600")
    void testCalculoAuto35Minutos() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        LocalDateTime fechaSalida = fechaEntrada.plusMinutes(35);
        v.setFechaHoraSalida(fechaSalida);
        v.registrarSalida();
        assertEquals(1600, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Moto: 1 minuto debería cobrar 1 bloque completo -> $500")
    void testCobroMotoMinimo() {
        Vehiculo v = new Vehiculo("TK-2", "MMM-222", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(1));
        v.registrarSalida();
        assertEquals(500, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Camioneta: Exactamente 30 min es 1 bloque -> $1000")
    void testCobroCamionetaBloqueExacto() {
        Vehiculo v = new Vehiculo("TK-3", "CCC-333", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(30)); 
        v.registrarSalida();
        assertEquals(1000, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Auto: 60 minutos deberían ser 2 bloques -> $1600")
    void testCalculoAuto60Minutos() {
        Vehiculo v = new Vehiculo("TK-7", "AAA-111", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(60));
        v.registrarSalida();
        assertEquals(1600, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Auto: 31 minutos deberían ser 2 bloques -> $1600")
    void testCalculoAuto31Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(31));
        v.registrarSalida();
        assertEquals(1600, v.calcularCobro());
    }

    @Test
    @DisplayName("Tope Máximo: No debe cobrar más de $15.000 aunque esté días")
    void testTopeMaximo() {
        Vehiculo v = new Vehiculo("TK-4", "RIC-000", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusHours(24));
        v.registrarSalida();
        assertEquals(15000, v.calcularCobro());
    }

    @Test
    @DisplayName("Debe manejar bien el cambio de estado")
    void testCambioDeEstado() {
        Vehiculo v = new Vehiculo("TK-5", "ZZZ-999", "Auto", fechaEntrada, "abierto");
        assertEquals("abierto", v.getEstado());
        v.registrarSalida();
        assertEquals("cerrado", v.getEstado());
    }

    @Test
    @DisplayName("Si no ha salido (fecha salida null), el cobro debe ser 0")
    void testCobroSinSalida() {
        Vehiculo v = new Vehiculo("TK-6", "PRO-000", "Auto", fechaEntrada, "abierto");
        assertEquals(0.0, v.calcularCobro());
    }
}