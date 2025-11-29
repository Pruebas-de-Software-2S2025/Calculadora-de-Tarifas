
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
            () -> assertEquals("ABC-123", v.getPlaca()),
            () -> assertEquals("Auto", v.getTipoVehiculo()),
            () -> assertEquals(fechaEntrada, v.getFechaHoraEntrada()),
            () -> assertEquals("abierto", v.getEstado()),
            () -> assertNull(v.getFechaHoraSalida(), "La fecha de salida debería ser null al inicio")
        );
    }

    @Test
    @DisplayName("Cálculo Auto: 0 min debería ser operación inválida y cobrar $0")
    void testCalculoAuto0Minutos() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada); 
        v.registrarSalida();
        assertEquals(0, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Auto: 30 min debería cobrar $800")
    void testCalculoAuto30Minutos() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        v.registrarSalida();
        assertEquals(800, v.calcularCobro());
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
    @DisplayName("Cálculo Auto: 60 minutos deberían ser 2 bloques -> $1600")
    void testCalculoAuto60Minutos() {
        Vehiculo v = new Vehiculo("TK-7", "AAA-111", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(60));
        v.registrarSalida();
        assertEquals(1600, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Auto: 61 minutos deberían ser 3 bloques -> $2400")
    void testCalculoAuto61Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(61));
        v.registrarSalida();
        assertEquals(2400, v.calcularCobro());
    }

    
    @Test
    @DisplayName("Cálculo Moto: 30 min debería cobrar $500")
    void testCalculoMoto30Minutos() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        v.registrarSalida();
        assertEquals(500, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Moto: 31 minutos deberían ser 2 bloques -> $1000")
    void testCalculoMoto31Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(31));
        v.registrarSalida();
        assertEquals(1000, v.calcularCobro());
    }
    

    @Test
    @DisplayName("Cálculo Moto: 60 minutos deberían ser 2 bloques -> $1000")
    void testCalculoMoto60Minutos() {
        Vehiculo v = new Vehiculo("TK-7", "AAA-111", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(60));
        v.registrarSalida();
        assertEquals(1000, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Moto: 61 minutos deberían ser 3 bloques -> $1500")
    void testCalculoMoto61Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(61));
        v.registrarSalida();
        assertEquals(1500, v.calcularCobro());
    }
    
    
    @Test
    @DisplayName("Cálculo Camioneta: 30 min debería cobrar $1000")
    void testCalculoCamioneta30Minutos() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        v.registrarSalida();
        assertEquals(1000, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Camioneta: 31 minutos deberían ser 2 bloques -> $2000")
    void testCalculoCamioneta31Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(31));
        v.registrarSalida();
        assertEquals(2000, v.calcularCobro());
    }
    

    @Test
    @DisplayName("Cálculo Camioneta: 60 minutos deberían ser 2 bloques -> $2000")
    void testCalculoCamioneta60Minutos() {
        Vehiculo v = new Vehiculo("TK-7", "AAA-111", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(60));
        v.registrarSalida();
        assertEquals(2000, v.calcularCobro());
    }

    @Test
    @DisplayName("Cálculo Camioneta: 61 minutos deberían ser 3 bloques -> $3000")
    void testCalculoCamioneta61Minutos() {
        Vehiculo v = new Vehiculo("TK-8", "BBB-222", "Camioneta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(61));
        v.registrarSalida();
        assertEquals(3000, v.calcularCobro());
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

    @Test
    @DisplayName("Fecha de salida anterior a la entrada debe dar cobro 0")
    void testSalidaAntesDeEntrada() {
        Vehiculo v = new Vehiculo("TK-10", "ERR-001", "Moto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.minusHours(1)); 
        v.registrarSalida();
        assertEquals(0, v.calcularCobro(), "El cobro debería ser 0 si la fecha de salida es inválida");
    }

    @Test
    @DisplayName("Tipo de vehículo desconocido debe dar cobro 0")
    void testVehiculoDesconocido() {
        Vehiculo v = new Vehiculo("TK-11", "DESC-00", "Bicicleta", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusHours(1));
        v.registrarSalida();
        assertEquals(0, v.calcularCobro(), "Un tipo de vehículo no configurado no debería generar cobro");
    }

    @Test
    @DisplayName("toString() debe mostrar 'En estacionamiento' para tickets abiertos")
    void testToStringAbierto() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        String esperado = "ID: TK-1 | Placa: ABC-123 | Tipo: Auto | Entrada: 01/01/2024 10:00 | Salida: En estacionamiento | Estado: abierto";
        assertTrue(v.toString().startsWith(esperado), "El formato de toString para un vehículo abierto es incorrecto.");
    }

    @Test
    @DisplayName("toString() debe mostrar el cobro para tickets cerrados")
    void testToStringCerrado() {
        Vehiculo v = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        v.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        v.registrarSalida();
        String textoTicket = v.toString();
        assertTrue(textoTicket.contains("| Estado: cerrado"), "El estado en toString debe ser 'cerrado'.");
        assertTrue(textoTicket.contains("| Cobro: $800"), "El cobro debe aparecer en el toString de un ticket cerrado.");
    }

    @Test
    @DisplayName("Calcular total recaudado de múltiples vehículos")
    void testTotalRecaudado() {
        Vehiculo auto1 = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "abierto");
        auto1.setFechaHoraSalida(fechaEntrada.plusMinutes(30)); // Deberia ser 800
        auto1.registrarSalida();
        
        Vehiculo moto1 = new Vehiculo("TK-2", "MTO-456", "Moto", fechaEntrada, "abierto");
        moto1.setFechaHoraSalida(fechaEntrada.plusMinutes(60)); // Deberia ser 1000
        moto1.registrarSalida();
        
        Vehiculo camioneta1 = new Vehiculo("TK-3", "CAM-789", "Camioneta", fechaEntrada, "abierto");
        camioneta1.setFechaHoraSalida(fechaEntrada.plusMinutes(31)); // Deberia ser 2000
        camioneta1.registrarSalida();
        
        
        double totalEsperado = 3800;
        double totalCalculado = auto1.calcularCobro() + moto1.calcularCobro() + camioneta1.calcularCobro();
        
        assertEquals(totalEsperado, totalCalculado);
    }

    @Test
    @DisplayName("Total recaudado con vehículos abiertos (no deben contar)")
    void testTotalRecaudadoSinAbiertos() {
        Vehiculo auto1 = new Vehiculo("TK-1", "ABC-123", "Auto", fechaEntrada, "cerrado");
        auto1.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        Vehiculo auto2 = new Vehiculo("TK-2", "XYZ-999", "Auto", fechaEntrada, "abierto");
        double totalSoloCerrados = auto1.calcularCobro();
        assertEquals(800, totalSoloCerrados, "Solo los vehículos cerrados deben contar en el total");
    }

    @Test
    @DisplayName("Total recaudado alcanza tope máximo")
    void testTotalRecaudadoConTopeMaximo() {
        Vehiculo camioneta = new Vehiculo("TK-1", "CAM-001", "Camioneta", fechaEntrada, "abierto");
        camioneta.setFechaHoraSalida(fechaEntrada.plusHours(48)); // 48 horas
        camioneta.registrarSalida();
        Vehiculo auto = new Vehiculo("TK-2", "AUTO-001", "Auto", fechaEntrada, "abierto");
        auto.setFechaHoraSalida(fechaEntrada.plusMinutes(30));
        auto.registrarSalida();
        double total = camioneta.calcularCobro() + auto.calcularCobro();
        
        assertEquals(15800, total, "El total debe incluir el tope máximo y otros cobros");
    }
}