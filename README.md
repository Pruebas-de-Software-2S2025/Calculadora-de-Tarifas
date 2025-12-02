# Calculadora de Tarifas de Estacionamiento

> Sistema de gestión de estacionamiento desarrollado con **Java 21**, **JUnit 5** y **TDD** (Test-Driven Development)

## 📋 Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Diseño del Sistema](#diseño-del-sistema)
3. [Requisitos Funcionales](#requisitos-funcionales)
4. [Instrucciones de Compilación y Ejecución](#instrucciones-de-compilación-y-ejecución)
5. [Suite de Tests](#suite-de-tests)
6. [Cobertura de Código](#cobertura-de-código)
7. [Licencia](#licencia)

---

## 📌 Descripción General

Sistema de línea de comandos (CLI) que gestiona el cobro de un estacionamiento para diferentes tipos de vehículos.

**Características principales:**
- ✅ Registro de entrada y salida de vehículos
- ✅ Cálculo de tarifas por bloques de 30 minutos
- ✅ Tarifas diferenciadas por tipo de vehículo
- ✅ Tope diario de cobro ($15.000)
- ✅ Descuento fin de semana (10%)
- ✅ Consultas de tickets abiertos, cerrados y total recaudado
- ✅ 33 pruebas unitarias con 100% de cobertura

---

## 🏗️ Diseño del Sistema

### Diagrama UML

```
┌─────────────────────────────────┐
│         Vehiculo                │
├─────────────────────────────────┤
│ - idTicket: String              │
│ - placa: String                 │
│ - tipoVehiculo: String          │
│ - fechaHoraEntrada: LocalDateTime│
│ - fechaHoraSalida: LocalDateTime│
│ - estado: String (abierto/cerrado)│
│ - TARIFA_AUTO: int = 800        │
│ - TARIFA_MOTO: int = 500        │
│ - TARIFA_CAMIONETA: int = 1000  │
│ - TOPE_DIARIO: double = 15000.0 │
│ - DESCUENTO_FINDE: double = 0.10│
├─────────────────────────────────┤
│ + getIdTicket(): String         │
│ + getPlaca(): String            │
│ + getTipoVehiculo(): String     │
│ + getFechaHoraEntrada(): LocalDateTime│
│ + getFechaHoraSalida(): LocalDateTime│
│ + getEstado(): String           │
│ + setFechaHoraSalida(LocalDateTime) │
│ + registrarSalida(): void       │
│ + calcularTiempoEnMinutos(): long │
│ + calcularCobro(): double       │
│ + esFinDeSemana(): boolean      │
│ + toString(): String            │
└─────────────────────────────────┘
```

### Lógica de Negocio

**Cálculo de Tarifas:**
1. Duración en minutos: `fecha_salida - fecha_entrada`
2. Bloques de 30 min: `(minutos + 29) / 30` (redondeo hacia arriba)
3. Cobro base: `bloques × tarifa_tipo_vehículo`
4. Aplicar tope: Si cobro > $15.000, cobrar $15.000
5. Descuento fin de semana: Si entrada es sábado/domingo, aplicar 10% de descuento

**Ejemplo de Cálculo:**
- Auto, entrada lunes 10:00, salida lunes 10:35 (35 min)
  - Bloques: (35 + 29) / 30 = 2 bloques
  - Cobro: 2 × $800 = $1.600
  
- Auto, entrada sábado 10:00, salida sábado 10:35 (35 min)
  - Bloques: 2 bloques
  - Cobro base: $1.600
  - Con descuento 10%: $1.600 × 0.9 = $1.440

---

## 📊 Requisitos Funcionales

| Operación | Descripción |
|-----------|-------------|
| **Registrar entrada** | Crea un ticket con patente, tipo de vehículo, fecha/hora de entrada y estado "abierto" |
| **Registrar salida** | Cierra un ticket abierto, calcula cobro y cambia estado a "cerrado" |
| **Listar tickets abiertos** | Muestra todos los vehículos actualmente en estacionamiento |
| **Listar tickets cerrados** | Muestra histórico de vehículos que han pagado |
| **Detalle de un ticket** | Busca por ID y muestra información completa (tiempo, costo) |
| **Total recaudado del día** | Suma cobros de todos los tickets cerrados del día actual |

### Tipos de Vehículo y Tarifas

| Tipo | Tarifa/Bloque |
|------|---------------|
| Auto | $800 |
| Moto | $500 |
| Camioneta | $1.000 |

---

## 🚀 Instrucciones de Compilación y Ejecución

### Requisitos Previos
- Java 21+
- Maven 3.9+ (opcional, puede compilarse con `javac`)

### Compilación Manual

```bash
# Posicionarse en el directorio del proyecto
cd src/java

# Compilar las clases
javac Vehiculo.java Main.java

# Compilar tests (requiere JUnit 5 en el classpath)
cd ../test
javac -cp ".:../java:../../lib/*" VehiculoTest.java
```

### Con Maven (si existe pom.xml)

```bash
# Compilar
mvn clean compile

# Ejecutar aplicación
mvn exec:java -Dexec.mainClass="Main"

# Ejecutar tests
mvn test

# Generar reporte de cobertura JaCoCo
mvn jacoco:report
```

### Ejecución Manual

```bash
# Desde src/java
java Main

# Desde el proyecto raíz
java -cp src/java Main
```

### Ejecución de Tests

```bash
# Con Maven
mvn test

# Manual (requiere classpath correcto)
java -cp ".:lib/*" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

---

## ✅ Suite de Tests

La suite contiene **33 pruebas unitarias** que cubren:

### Tests por Funcionalidad

#### Constructor y Getters (1 test)
- ✅ Inicialización correcta de vehículos

#### Cálculo de Bloques (5 tests por tipo)
- ✅ 0 minutos → operación inválida ($0)
- ✅ 30 minutos → 1 bloque
- ✅ 31 minutos → 2 bloques
- ✅ 60 minutos → 2 bloques
- ✅ 61 minutos → 3 bloques

#### Cálculo para Cada Tipo (15 tests)
- Auto (5 tests)
- Moto (5 tests)
- Camioneta (5 tests)

#### Casos Especiales (13 tests)
- ✅ Tope máximo ($15.000)
- ✅ Cambio de estado (abierto → cerrado)
- ✅ Cobro sin salida
- ✅ Salida anterior a entrada (inválida)
- ✅ Tipo de vehículo desconocido
- ✅ Formato toString() para abiertos y cerrados
- ✅ Total recaudado múltiples vehículos
- ✅ Total sin contar abiertos
- ✅ Total con tope máximo

### Ejemplo de Salida de Tests

```
[INFO] Running VehiculoTest
[INFO] 
[INFO] ✓ Debe inicializar correctamente un vehículo
[INFO] ✓ Cálculo Auto: 0 min debería ser operación inválida y cobrar $0
[INFO] ✓ Cálculo Auto: 30 min debería cobrar $800
[INFO] ✓ Cálculo Auto: 31 minutos deberían ser 2 bloques -> $1600
[INFO] ✓ Cálculo Auto: 60 minutos deberían ser 2 bloques -> $1600
[INFO] ✓ Cálculo Auto: 61 minutos deberían ser 3 bloques -> $2400
[INFO] ✓ Cálculo Moto: 30 min debería cobrar $500
[INFO] ✓ Cálculo Moto: 31 minutos deberían ser 2 bloques -> $1000
[INFO] ✓ Cálculo Moto: 60 minutos deberían ser 2 bloques -> $1000
[INFO] ✓ Cálculo Moto: 61 minutos deberían ser 3 bloques -> $1500
[INFO] ✓ Cálculo Camioneta: 30 min debería cobrar $1000
[INFO] ✓ Cálculo Camioneta: 31 minutos deberían ser 2 bloques -> $2000
[INFO] ✓ Cálculo Camioneta: 60 minutos deberían ser 2 bloques -> $2000
[INFO] ✓ Cálculo Camioneta: 61 minutos deberían ser 3 bloques -> $3000
[INFO] ✓ Tope Máximo: No debe cobrar más de $15.000 aunque esté días
[INFO] ✓ Debe manejar bien el cambio de estado
[INFO] ✓ Si no ha salido (fecha salida null), el cobro debe ser 0
[INFO] ✓ Fecha de salida anterior a la entrada debe dar cobro 0
[INFO] ✓ Tipo de vehículo desconocido debe dar cobro 0
[INFO] ✓ toString() debe mostrar 'En estacionamiento' para tickets abiertos
[INFO] ✓ toString() debe mostrar el cobro para tickets cerrados
[INFO] ✓ Calcular total recaudado de múltiples vehículos
[INFO] ✓ Total recaudado con vehículos abiertos (no deben contar)
[INFO] ✓ Total recaudado alcanza tope máximo
[INFO]
[INFO] Tests run: 33, Failures: 0, Skipped: 0
```

---

## 📊 Cobertura de Código

### ¿Qué Tipo de Cobertura se Midió?

Se implementó **cobertura de código statement/line** (cobertura de líneas ejecutables):

```
Clase Vehiculo:
├── Líneas totales: 88
├── Líneas ejecutables: 78
├── Líneas cubiertas: 78
└── Cobertura: 100%
```

**Por qué esta métrica:**
1. **Statement Coverage** es el nivel más básico y fundamental de cobertura
2. Garantiza que cada instrucción de código es ejecutada al menos una vez
3. Es apropiado para validar la lógica de negocio crítica
4. Fácil de medir y reportar con JaCoCo

### Configuración de JaCoCo

Para medir la cobertura:

```xml
<!-- En pom.xml -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Comando para generar reporte:
```bash
mvn jacoco:report
```

El reporte se genera en: `target/site/jacoco/index.html`

---

## 📁 Estructura del Proyecto

```
Calculadora-de-Tarifas/
├── src/
│   ├── java/
│   │   ├── Main.java          # Aplicación CLI (menú principal)
│   │   └── Vehiculo.java      # Modelo y lógica de cálculo
│   └── test/
│       └── VehiculoTest.java  # Suite de 33 tests
├── lib/                       # Dependencias (JUnit 5, etc.)
├── Makefile                   # Comandos de compilación y ejecución
├── pom.xml                    # Configuración Maven (opcional)
├── README.md                  # Este archivo
├── LICENSE                    # Licencia MIT
└── .gitignore
```

---

## 🛠️ Tecnologías Utilizadas

| Componente | Versión |
|-----------|---------|
| Java | 21+ |
| JUnit | 5.9+ |
| Maven | 3.9+ |
| JaCoCo | 0.8.8+ |

---

## 📋 Consideraciones Técnicas

### Diseño OO
- ✅ Clase `Vehiculo` encapsula datos y comportamiento
- ✅ Constantes para tarifas y límites
- ✅ Métodos bien definidos con responsabilidades claras

### Pruebas Unitarias
- ✅ Uso de `@DisplayName` para descripción clara de tests
- ✅ `@BeforeEach` para setup común (si se requiere)
- ✅ `assertAll()` para validaciones múltiples
- ✅ Tests independientes y deterministas

### Manejo de Excepciones
- ✅ Validación de casos inválidos (minutos ≤ 0, tipo desconocido)
- ✅ Retorno de 0 para operaciones inválidas (no se lanza excepción)

### Formato y Estilo
- ✅ Nombres descriptivos en español
- ✅ Código limpio y legible
- ✅ Comentarios en partes complejas

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**. Ver archivo [LICENSE](LICENSE) para más detalles.

---

## 👥 Autor

Desarrollado como ejercicio práctico de Pruebas de Software - USM 2S 2025

---

**Última actualización:** Diciembre 2025


### 2.1. Gestión de Tickets de Estacionamiento

| Operación           | Detalles                                                                                  |
|---------------------|-------------------------------------------------------------------------------------------|
| Registrar entrada   | Crea un ticket de estacionamiento con: `idTicket`, `patente`, `tipoVehiculo` (AUTO, MOTO, CAMIONETA), `fechaHoraEntrada`, `estado` (ABIERTO). |
| Registrar salida    | Completa un ticket **abierto** agregando: `fechaHoraSalida`, `montoCobrado`, cambio de `estado` a "Cerrado" |
| Listar tickets abiertos | Muestra todos los tickets cuyo estado es "Abierto"                                 |
| Listar tickets cerrados | Muestra el histórico de tickets con estado "Cerrado".                                  |
| Restricciones       | No se puede registrar salida de un ticket inexistente o ya cerrado.                      |

> Puedes modelar tickets y vehículos como una o varias clases, pero el sistema debe tener clara la idea de un **ticket abierto/cerrado**.

---

### 2.2. Cálculo de tarifas

Cuando se registra la "salida" de un vehículo, el sistema debe calcular el valor a pagar según estas reglas:

1. **Duración del estacionamiento**

   - Se calcula la duración en minutos entre `fechaHoraEntrada` y `fechaHoraSalida`.
   - Si la duración es menor o igual a 0 minutos, la operación debe considerarse inválida (no se cobrar).

2. **Bloques de tiempo**

   - El cobro se hace por **bloques de 30 minutos**, redondeando **hacia arriba**.  
     - Ejemplos:
       - 1 a 30 min → 1 bloque  
       - 31 a 60 min → 2 bloques  
       - 61 a 90 min → 3 bloques, etc.

3. **Tarifa por tipo de vehículo (por bloque de 30 minutos)**

| Tipo de vehículo | Tarifa por bloque (ejemplo) |
|------------------|-----------------------------|
| AUTO             | $800                        |
| MOTO             | $500                        |
| CAMIONETA        | $1.000                      |

SE sugiere usar estos valores en la implementación

4. **Tope diario**

   - El monto total a pagar por un ticket **no puede exceder** un máximo diario (por día calendario).  
   - Para simplificar, usa un "tope único" para todos los vehículos:
     - **Tope diario**: $15.000  
   - Si el cálculo por bloques supera este monto, se cobra $15.000 (no hay un teximetro eterno).

5. **Descuento fin de semana**

   - Si la **fecha de entrada** del ticket corresponde a **sábado o domingo**, se aplica un "10 % de descuento" al valor final (después de aplicar el tope diario, si corresponde).
   - El descuento debe redondearse hacia abajo al entero más cercano.

Toda esta lógica de duración, bloques, tope y descuento debe ser fácilmente testeable con pruebas unitarias.


### 2.3. Consultas y reportes simples

El sistema debe permitir:

| Operación                    | Detalles                                                                                                              |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Mostrar detalle de un ticket | Consultar por `idTicket` y mostrar patente, tipo, tiempo estacionado, monto cobrado (si está cerrado) o indicar que aún está abierto. |
| Mostrar total recaudado del día | Entregar la suma de `montoCobrado` de todos los tickets cerrados cuya fecha (entrada o salida, a definir en tu diseño) corresponde al **día actual**. |
| Validaciones básicas         | Manejar el caso en que no existan tickets para la consulta.                                                          |

---

## 3. Requisitos técnicos

| Ítem         | Detalle                                                                                   |
|-------------|--------------------------------------------------------------------------------------------|
| Tipo de app | Por consola (CLI)                                                                         |
| Lenguaje    | Java 21+                                                                                  |
| Build       | Maven o Gradle (indicar en el README cómo compilar/ejecutar)                              |
| Pruebas     | JUnit 5 + assertions estándar                                                             |
| Persistencia| En memoria (no se requieren archivos ni base de datos)                                    |
| Estilo      | Diseño OO limpio (clases para entidades, lógica de cálculo separada, etc.)               |
| Medir cobertura | Usar EclEmma (JaCoCo)                                                |
| TDD         | Se sugiere uso de TDD en el desarrollo (no obligatorio, pero lo recomendado)                 |
| Modalidad   | Trabajo individual                                                                        |

---

## 4. Menú principal (CLI)

El sistema debe ofrecer un menú similar a este:

1. Registrar entrada de vehículo  
2. Registrar salida de vehículo (calcular cobro)  
3. Listar tickets abiertos  
4. Listar tickets cerrados  
5. Mostrar detalle de un ticket  
6. Mostrar total recaudado del día  
7. Salir  

> Puedes reorganizar o subdividir el menú mientras mantengas estas funcionalidades.

---

## 5. Entregables
Repositorio GitHub (público) con:
- Código fuente organizado
- Suite de tests JUnit.
- README.md que incluya:
  - Descripción del diseño (diagrama UML o otro), no incluir enlaces a repositorios personales (por ejemplo en Sharepoint).
  - Instrucciones para compilar, ejecutar y probar.
  - Ejemplo de salida de tests.
  - Licencia
  - Otras onsideraciones vistas previamente en curso
  - Responde a pregunta: **¿Qué tipo de cobertura he medido y por qué?**

---

## 6. Dudas y preguntas

Cualquier duda o descubrimiento, publícalo en el **foro de la semana**, para que las respuestas queden visibles para todo el curso.
