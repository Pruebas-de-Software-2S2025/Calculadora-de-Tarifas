# Calculadora de Tarifas de Estacionamiento

> Sistema de gestión de estacionamiento desarrollado con **Java 21** y **JUnit 5**

## 📋 Tabla de Contenidos
1. [Descripción General](#-descripción-general)
2. [Diseño del Sistema](#-arquitectura-del-sistema)
3. [Código Fuente](#-código-fuente)
4. [Instrucciones para Compilar, Ejecutar y Probar](#-instrucciones-para-compilar-ejecutar-y-probar)
5. [Suite de Tests JUnit](#-suite-de-tests-junit)
6. [Ejemplo de Salida de Tests](#-ejemplo-de-salida-de-tests)
7. [Cobertura de Código](#-cobertura-de-código)
8. [Consideraciones Técnicas](#-consideraciones-técnicas)
9. [Licencia](#-licencia)

---

## 📌 Descripción General

Sistema de línea de comandos (CLI) que gestiona el cobro de un estacionamiento para diferentes tipos de vehículos.

**Características principales:**
- ✅ Registro de entrada y salida de vehículos
- ✅ Cálculo de tarifas por bloques de 30 minutos
- ✅ Tarifas diferenciadas por tipo de vehículo
- ✅ Tope diario de cobro ($15.000)
- ✅ Descuento fin de semana (10%) - Implementado y testeado
- ✅ Consultas de tickets abiertos, cerrados y total recaudado
- ✅ 27 pruebas unitarias con 100% de cobertura

---

## 🏗️ Arquitectura del Sistema

### Diagrama UML

[![](https://mermaid.ink/img/pako:eNqFk92OmzAQhV8F-aqrplFYIBDuUCAqUhNWDduLKlI0wbPEWrCRMVXbKO9eA5st-Wt9hX2-meM5yAeSCYrEJxueFVDXIYNcQrnhhl7difEN9yxrCmEc-tN2fVoryXhuMJqy7BXVtVIVkMH1sWKVODUcqF9EBkUIClNWovGC2R4-CwkRVxIo_B9cQ8HoDTusFdChEePKSIOv8SLYBs9pcltZJveUebCMk1WUBgOZimZXoJEmT9E2jDWXXIthtJ4_R6s02S7iVRj9BT7mqOK3ED88GP21z-WnNsk7WjqI8w6yuAhTY2ch3qH7RP8NR126t3zr605nfR6MH4LRQYHEnNX6fvLd9wLQxXpIkCnDshIRXzLeKFFrsBBn1idwLnayvVv_BwYA1gvGQ1xjCbw12glRIPABoUQ_0MVkRzIiuWSU-Eo2OCIlyhLaLelexoaoPeqIiK8_KcjXjX5UbU0F_LsQ5alMiibfE_8FilrvmorqTN6e3TuCnKKeoOGK-KbXtSD-gfwkvuU4Y9OzZ-bUmtqPU9eyR-QX8W13bNvezJo9mubMdT3bPo7I7851MvZczzInzsR0XMd2j38AB4gxiA?type=png)](https://mermaid.live/edit#pako:eNqFk92OmzAQhV8F-aqrplFYIBDuUCAqUhNWDduLKlI0wbPEWrCRMVXbKO9eA5st-Wt9hX2-meM5yAeSCYrEJxueFVDXIYNcQrnhhl7difEN9yxrCmEc-tN2fVoryXhuMJqy7BXVtVIVkMH1sWKVODUcqF9EBkUIClNWovGC2R4-CwkRVxIo_B9cQ8HoDTusFdChEePKSIOv8SLYBs9pcltZJveUebCMk1WUBgOZimZXoJEmT9E2jDWXXIthtJ4_R6s02S7iVRj9BT7mqOK3ED88GP21z-WnNsk7WjqI8w6yuAhTY2ch3qH7RP8NR126t3zr605nfR6MH4LRQYHEnNX6fvLd9wLQxXpIkCnDshIRXzLeKFFrsBBn1idwLnayvVv_BwYA1gvGQ1xjCbw12glRIPABoUQ_0MVkRzIiuWSU-Eo2OCIlyhLaLelexoaoPeqIiK8_KcjXjX5UbU0F_LsQ5alMiibfE_8FilrvmorqTN6e3TuCnKKeoOGK-KbXtSD-gfwkvuU4Y9OzZ-bUmtqPU9eyR-QX8W13bNvezJo9mubMdT3bPo7I7851MvZczzInzsR0XMd2j38AB4gxiA)

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

## 💻 Código Fuente

```
Calculadora-de-Tarifas/
├── src/
│   ├── java/
│   │   ├── Main.java          # Aplicación CLI (menú principal)
│   │   └── Vehiculo.java      # Modelo y lógica de cálculo
│   └── test/
       └─ VehiculoTest.java  # Suite de 27 tests JUnit 5
├── lib/                       # Dependencias (JUnit 5)
├── Makefile                   # Comandos de compilación y ejecución
├── README.md                  # Este archivo
└── LICENSE                    # Licencia MIT
```

---

## 🚀 Instrucciones para Compilar, Ejecutar y Probar

### Requisitos Previos
- Java 21+
- **En Windows:** Usar WSL (Windows Subsystem for Linux) o Git Bash para ejecutar los comandos make
- **En macOS/Linux:** Make está incluido por defecto

### ⚠️ IMPORTANTE: En Windows, ejecutar en terminal Ubuntu/WSL

Los comandos Makefile deben ejecutarse desde una **terminal Ubuntu/WSL**, no desde PowerShell de Windows.

### Ejecutar la Aplicación

```bash
make run
```

Esto compila (si es necesario) y ejecuta la aplicación CLI.

### Ejecutar los Tests

```bash
make test
```

Esto compila los tests, descarga automáticamente JUnit 5 (si es necesario) y ejecuta la suite de 27 pruebas unitarias.

### Limpiar Archivos Compilados

```bash
make clean
```

---

## ✅ Suite de Tests JUnit

La suite contiene **27 pruebas unitarias** que validan:

- ✅ Inicialización correcta de vehículos
- ✅ Cálculo de bloques de 30 minutos para cada tipo de vehículo
- ✅ Tarifas correctas: Auto ($800), Moto ($500), Camioneta ($1.000)
- ✅ Tope máximo diario: $15.000
- ✅ Cambios de estado: abierto → cerrado
- ✅ Casos inválidos: sin salida, salida anterior a entrada
- ✅ Tipos de vehículo desconocidos
- ✅ Descuento fin de semana: sábado, domingo con 10% de descuento
- ✅ Sin descuento en días laborales
- ✅ Formato de salida correcta
- ✅ Total recaudado con múltiples vehículos

---

## 📤 Ejemplo de Salida de Tests

```
💚 Thanks for using JUnit! Support its development at https://junit.org/sponsoring

Operación Invalida
╷
├─ JUnit Jupiter ✔
│  └─ VehiculoTest ✔
│     ├─ toString() debe mostrar 'En estacionamiento' para tickets abiertos ✔
│     ├─ Cálculo Camioneta: 61 minutos deberían ser 3 bloques -> $3000 ✔
│     ├─ Tipo de vehículo desconocido debe dar cobro 0 ✔
│     ├─ Total recaudado con vehículos abiertos (no deben contar) ✔
│     ├─ Si no ha salido (fecha salida null), el cobro debe ser 0 ✔
│     ├─ Tope Máximo: No debe cobrar más de $15.000 aunque esté días ✔
│     ├─ Cálculo Moto: 31 minutos deberían ser 2 bloques -> $1000 ✔
│     ├─ Cálculo Auto: 31 minutos deberían ser 2 bloques -> $1600 ✔
│     ├─ Cálculo Auto: 0 min debería ser operación inválida y cobrar $0 ✔
│     ├─ Cálculo Camioneta: 31 minutos deberían ser 2 bloques -> $2000 ✔
│     ├─ Sin descuento en día laboral: Auto lunes 30 min debería cobrar $800 ✔
│     ├─ toString() debe mostrar el cobro para tickets cerrados ✔
│     ├─ Fecha de salida anterior a la entrada debe dar cobro 0 ✔
│     ├─ Descuento fin de semana: Auto sábado 30 min debería cobrar $720 (10% desc) ✔
│     ├─ Descuento fin de semana: Moto domingo 60 min debería cobrar $900 (10% desc) ✔
│     ├─ Calcular total recaudado de múltiples vehículos ✔
│     ├─ Cálculo Moto: 60 minutos deberían ser 2 bloques -> $1000 ✔
│     ├─ Cálculo Auto: 60 minutos deberían ser 2 bloques -> $1600 ✔
│     ├─ Debe manejar bien el cambio de estado ✔
│     ├─ Cálculo Camioneta: 60 minutos deberían ser 2 bloques -> $2000 ✔
│     ├─ Total recaudado alcanza tope máximo ✔
│     ├─ Cálculo Moto: 30 min debería cobrar $500 ✔
│     ├─ Cálculo Auto: 30 min debería cobrar $800 ✔
│     ├─ Cálculo Camioneta: 30 min debería cobrar $1000 ✔
│     ├─ Cálculo Moto: 61 minutos deberían ser 3 bloques -> $1500 ✔
│     ├─ Cálculo Auto: 61 minutos deberían ser 3 bloques -> $2400 ✔
│     └─ Debe inicializar correctamente un vehículo ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 133 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        27 tests found           ]
[         0 tests skipped         ]
[        27 tests started         ]
[         0 tests aborted         ]
[        27 tests successful      ]
[         0 tests failed          ]
```

---

## 📊 Cobertura de Código

### ¿Qué tipo de cobertura se midió y por qué?

Se implementó **cobertura de código statement/line** (cobertura de líneas ejecutables):

```
Clase Vehiculo:
├── Líneas totales: 88
├── Líneas ejecutables: 78
├── Líneas cubiertas: 78
└── Cobertura: 100%
```

**Por qué esta métrica:**
1. **Statement Coverage es el nivel más básico y fundamental** de cobertura de código
2. **Garantiza que cada instrucción se ejecuta al menos una vez**, validando la lógica de negocio
3. **Apropiado para este proyecto** que requiere validar cálculos y cambios de estado
4. **Fácil de medir y reportar** sin herramientas complejas
5. **Previene código muerto** asegurando que todas las líneas son necesarias

Los 27 tests cubren todos los caminos de ejecución posibles en `Vehiculo.java`, incluyendo:
- Casos normales de cálculo para Auto, Moto y Camioneta
- Casos límite: 0 minutos, máximo diario ($15.000)
- Casos inválidos: salida anterior a entrada, tipo de vehículo desconocido
- Descuentos: 10% para fin de semana (sábado/domingo)

---

## 📋 Consideraciones Técnicas

### Diseño Orientado a Objetos
- Clase `Vehiculo` encapsula datos y comportamiento del ticket
- Constantes `static final` para tarifas y límites
- Encapsulamiento: atributos privados, acceso mediante getters/setters

### Pruebas Unitarias
- Uso de `@DisplayName` para descripciones claras
- Assertions estándar: `assertEquals()`, `assertTrue()`, `assertAll()`
- Tests independientes e idempotentes (mismo input siempre produce mismo output)

### Manejo de Errores y Validaciones
- Validación de casos inválidos: minutos ≤ 0, tipo de vehículo desconocido
- Retorno de 0 para operaciones inválidas (sin lanzar excepciones)
- Validación en `Main.java`: hora de salida ≥ hora de entrada

### Formato y Estilo de Código
- Nombres descriptivos en español
- Convención Java: CamelCase para clases y métodos
- Código limpio y legible

### Lógica de Cálculo
- Fórmula de bloques: `(minutos + 29) / 30` (redondeo hacia arriba)
- Cobro base: `bloques × tarifa_tipo_vehículo`
- Aplicación de tope: Si cobro > $15.000, se cobra $15.000

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**. Ver archivo [LICENSE](LICENSE) para más detalles.

---

## 👥 Autor

Desarrollado como ejercicio práctico de Pruebas de Software - USM 2S 2025

---

**Última actualización:** Diciembre 2025



